# /////////////////////////////////////////////////////////////////////////////
# REFCODES.ORG
# =============================================================================
# This code is copyright (c) by Siegfried Steiner, Munich, Germany and licensed
# under the following (see "http://en.wikipedia.org/wiki/Multi-licensing")
# licenses:
# =============================================================================
# GNU General Public License, v3.0 ("http://www.gnu.org/licenses/gpl-3.0.html")
# =============================================================================
# Apache License, v2.0 ("http://www.apache.org/licenses/LICENSE-2.0")
# =============================================================================
# Please contact the copyright holding author(s) of the software artifacts in
# question for licensing issues not being covered by the above listed licenses,
# also regarding commercial licensing models or regarding the compatibility
# with other open source licenses.
# /////////////////////////////////////////////////////////////////////////////
#!/bin/bash
# INIT:
CURRENT_DIR="$(pwd)"
SCRIPT_PATH="$(dirname $0)"
cd "${SCRIPT_PATH}"
SCRIPT_PATH="$(pwd)"
cd "${CURRENT_DIR}"
SCRIPT_DIR="${SCRIPT_PATH##*/}"
SCRIPT_NAME="$(basename $0 .sh)"
PARENT_PATH="$(realpath $(dirname $0)/..)"
PARENT_DIR="${PARENT_PATH##*/}"
# figlet -w 180 "/${PARENT_DIR}:>>>${SCRIPT_NAME}..."
# USAGE:
function usage {
	echo "> Usage: ${SCRIPT_NAME} <modulePath>"
}
# MAIN:
modulePath="$1"
if [ -z "${modulePath}" ] ; then
	modulePath=${SCRIPT_PATH}
	# echo "> You must provide a <modulePath>, aborting!" 1>&2;
	# usage
	# exit 1
fi
moduleName=$(basename ${modulePath})
targetFolder=$(readlink -m "${modulePath}/target")
if [ ! -e "${targetFolder}" ] ; then
	echo "> Folder <${targetFolder}> does not exist, aborting!" 1>&2;
	exit 1
fi
jarFile=$(find "${targetFolder}" -name "${moduleName}*.jar" ! -name "*tests.jar" ! -name "*sources.jar")
binName="$(echo "${moduleName}" | cut -d- -f2- ).sh"
if [ -z "${binName}" ]; then
	binName="${moduleName}.sh"
fi
binFile="${targetFolder}/${binName}"
if [ ! -e "${jarFile}" ] ; then
	echo "> No JAR file found for module <${moduleName}> in folder <${targetFolder}>, aborting!" 1>&2;
	exit 1
fi
isExecutable=$(unzip -q -c ${jarFile} META-INF/MANIFEST.MF | grep "Main-Class:")
if [ -z "${isExecutable}" ] ; then
    echo "> JAR file at <${jarFile}> is not executable, aborting!" 1>&2;
	exit 1
fi
cat ${SCRIPT_PATH}/shell-exec.inc ${jarFile} > ${binFile} && chmod +x ${binFile}
echo "> Created executable binary bash script <${binName}> at <${targetFolder}>"
# echo "> Done."