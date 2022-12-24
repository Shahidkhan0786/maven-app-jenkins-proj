// CODE_CHANNGES = getChanges()
def gv
pipeline {
    agent any
    tools {
        maven 'maven3.8'
    }
    environment {
        // define new environment variable here for all stages on pipeline 
        NEW_VERSION = '1.0.1'
    }
    parameters {
        choice(name:'Version',choices:['1.0.1', '1.1.0','1.2.0'] , description:"selet the version")
        booleanParam(name: 'executeTests', defaultValue:true , description:"execute the tests or not")
    }
    stages {
        stage('init'){
         steps {
            script{
            gv = load 'script.groovy' 
         }
         }
        }
        stage('build') {
            steps {
                echo 'building the app....'
                script {

                gv.buildjar()
                }
            }
        }
        // stage('test') {
        //     when {
        //         expression {
        //             // BRANCH_NAME == 'DEV' || BRANCH_NAME == "master"
        //             // BRANCH_NAME with variable 
        //             // BRANCH_NAME == 'dev' && CODE_CHANNGES == true
        //             params.executeTests == true
        //         }
        //     }
        //     steps {
        //         echo 'app is  in testing ....'
        //         // echo " to print env variable ${NEW_VERSION}"z
        //     }
        // }
        stage('pushtodockerhub') {
                steps {
                    echo 'app is deploy in dev ....'
                    script{

                    gv.buildImage()
                    }
                }
            }
        stage('prod') {
                steps {
                    echo 'app is deploying in prod...'
                    echo "app is deploying in prod version ${params.Version}..."
                }
            }
    }
}
