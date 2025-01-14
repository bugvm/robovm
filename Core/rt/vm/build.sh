SELF=$(basename $0)
BASE=$(cd $(dirname $0); pwd -P)
CLEAN=0
VERBOSE=

function usage {
  cat <<EOF
Usage: $SELF [options]
Options:
  --build=[release|debug] Specifies the build type. If not set both release
                          and debug versions of the libraries will be built.
  --target=...            Specifies the target(s) to build for. Supported 
                          targets are macosx-x86_64, ios-x86_64, ios-arm64, linux-x86_64.
                          Enclose multiple targets in quotes and
                          separate with spaces or specify --target multiple
                          times. If not set the current host OS determines the
                          targets. macosx-x86_64, ios-x86_64, and ios-arm64 on MacOSX and
                          linux-x86_64 on Linux.
  --verbose               Enable verbose output during the build.
  --clean                 Cleans the build dir before starting the build.
  --help                  Displays this information and exits.
EOF
  exit $1
}

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--target') TARGETS="$TARGETS $VALUE" ;;
    '--clean') CLEAN=1 ;;
    '--verbose') VERBOSE=VERBOSE=1 ;;
    '--build') BUILDS="$BUILDS $VALUE" ;;
    '--help')
      usage 0
      ;;
    *)
      echo "Unrecognized option or syntax error in option '$1'"
      usage 1
      ;;
  esac
  shift
done

if [ $(uname) = 'Darwin' ]; then
  if xcrun --sdk macosx --show-sdk-version &> /dev/null; then
    MACOSX_SDK_VERSION=$(xcrun --sdk macosx --show-sdk-version)
  else
    MACOSX_SDK_VERSION=10.13
  fi
  if xcrun --sdk iphoneos --show-sdk-version &> /dev/null; then
    IOS_SDK_VERSION=$(xcrun --sdk iphoneos --show-sdk-version)
  else
    IOS_SDK_VERSION=12.0
  fi
  if xcrun -f clang &> /dev/null; then
    CC=$(xcrun -f clang)
  else
    CC=$(which clang)
  fi
  if xcrun -f clang++ &> /dev/null; then
    CXX=$(xcrun -f clang++)
  else
    CXX=$(which clang++)
  fi
else
  CC=$(which clang)
  CXX=$(which clang++)
fi

if [ "x$TARGETS" = 'x' ]; then
  OS=$(uname)
  case $OS in
  Darwin)
    TARGETS="ios-arm64 ios-x86_64 macosx-x86_64"
    ;;
  Linux)
    TARGETS="linux-x86_64"
    ;;
  *)
    echo "Unsupported OS: $OS"
    exit 1
    ;;
  esac
fi
if [ "x$BUILDS" = 'x' ]; then
  BUILDS="release"
  #BUILDS="debug release"
fi

# Validate targets #linux-(x86_64)
for T in $TARGETS; do
  if ! [[ $T =~ (macosx-(x86_64))|(ios-(x86_64|arm64))|(linux-(x86_64)) ]] ; then
    echo "Unsupported target: $T"
    exit 1
  fi
done

# Validate build types
for B in $BUILDS; do
  if ! [[ $B =~ (debug|release) ]] ; then
    echo "Unsupported build type: $B"
    exit 1
  fi
done

mkdir -p "$BASE/target/build"
if [ "$CLEAN" = '1' ]; then
  for T in $TARGETS; do
    for B in $BUILDS; do
      rm -rf "$BASE/target/build/$T-$B"
    done
  done
fi

for T in $TARGETS; do
  OS=${T%%-*}
  ARCH=${T#*-}
 for B in $BUILDS; do
    BUILD_TYPE=$B
    mkdir -p "$BASE/target/build/$T-$B"
    rm -rf "$BASE/binaries/$OS/$ARCH/$B"
    bash -c "cd '$BASE/target/build/$T-$B'; cmake -DCMAKE_C_COMPILER=$CC -DCMAKE_CXX_COMPILER=$CXX -DCMAKE_BUILD_TYPE=$BUILD_TYPE -DMACOSX_SDK_VERSION=$MACOSX_SDK_VERSION -DIOS_SDK_VERSION=$IOS_SDK_VERSION -DOS=$OS -DARCH=$ARCH '$BASE'; make $VERBOSE install"
    R=$?
    if [[ $R != 0 ]]; then
      echo "$T-$B build failed"
      exit $R
    fi
  done
done