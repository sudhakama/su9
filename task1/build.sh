# /////////////////////////////////////////////////////////////////////////////
# FUNCODES.DE
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
figlet -w 180 "/${PARENT_DIR}:>>>${SCRIPT_NAME}..." 2> /dev/null
if [ $? -ne 0 ]; then
    banner "${SCRIPT_NAME}..." 2> /dev/null
    if [ $? -ne 0 ]; then
    	echo "> ${SCRIPT_NAME}:" | tr a-z A-Z 
	fi
fi
# COMMON:
MODULE_NAME=$(basename ${SCRIPT_DIR})
# MAIN:
echo "> Building \"${MODULE_NAME}\"..."
cd "${SCRIPT_PATH}"
mvn clean install "$@"
if (($? != 0)); then
	echo "> Failed to build <${MODULE_NAME}>, aborting!" 1>&2;
	exit 1
fi
# SHELLIFY:
# echo "> Processing <${MODULE_NAME}> ..."
"${SCRIPT_PATH}/shell-exec.sh" "${SCRIPT_PATH}"
if (($? != 0)); then
	echo "> Failed to process <${MODULE_NAME}>, aborting!" 1>&2;
	exit 1
fi
# END:
cd "${SCRIPT_PATH}"
# echo "> Done."
