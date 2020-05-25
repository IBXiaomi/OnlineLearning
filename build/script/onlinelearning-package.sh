# bash

# 执行打包脚本
current_path=`pwd`
package_param=`cd ../conf/onlineLearning-package.conf`
function main(){
    echo "start to exec zip package"
    awk $package_param | while read line
    do
        echo "get package_param is $line"
        cd ../../$line
        mvn clean package -Dmaven.test.skip=true
    done
}

main