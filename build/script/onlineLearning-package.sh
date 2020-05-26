#!/usr/bin/env bash

DIRNAME=$(dirname $0)
CURRENT_PATH=$(cd ${DIRNAME}; pwd)
CODE_HOME=$(cd ${CURRENT_PATH}../../)
CILog=${CODE_HOME}/log/CILog.log

# # # # # # # # # # # # #
#     function showLog  #
# # # # # # # # # # # # #
function showLog(){
    local current_user=$(whoami | awk '{print $1}')
    local current_user_ip=$(who am i | awk -F ' ' '{print $NF}' | grep -E "[0-9\.]*")
    printf "[`date '+%Y-%m-%d %H:%M:%S'`]-[${current_user}]-[${current_user_ip}] - $1 \n"
    echo "[`date '+%Y-%m-%d %H:%M:%S'`]-[${current_user}]-[${current_user_ip}] - $1 \n" >> ${CILog}
}


# # # # # # # # # # # # #
#     function package  #
# # # # # # # # # # # # #

function package(){
    #local package_param=${CODE_HOME}/build/conf/onlineLearning-package.conf
    showLog "start to exec zip package"
    # The current simple mvn command is executed
    cd ${CODE_HOME}
    mvn clean compile -Dmaven.test.skip=true
    if [ $? -eq 0 ];then
        showLog "mvn exec success,package success"
    else
        showLog "mvn exec failed,package failed"
    fi

    #awk '1' ${package_param} | while read line
    #do
        #showLog "get package_param is ${line}"
        #cd ../../$line
       # mvn clean package -Dmaven.test.skip=true
    #done
}

# # # # # # # # # # # # #
#      function init    #
# # # # # # # # # # # # #
function init(){
    if [ ! -d ${CILog}../ ];then
        showLog "CILog  dir is not exist will be create"
        mkdir -p ${CILog}../
    else
        showLog "CILog dir is exist will delete"
        rm -rf ${CILog}
        mkdir -p ${CILog}../
    fi
    
    if [ ! -f ${CILog} ]; then
        showLog "CILog  ${CILog}  file is not exist will be create"
       touch ${CILog}
    else
        showLog "CILog ${CILog}  file is exist will delete"
        rm -rf ${CILog}
        touch ${CILog}
    fi

}

# # # # # # # # # # # # #
#    function main      #
# # # # # # # # # # # # #
function main(){
    init
    package
}

main