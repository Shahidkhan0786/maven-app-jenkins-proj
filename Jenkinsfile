#!/usr/bin/env groovy

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
            when {
                expression {
                    BRANCH_NAME == 'master'
                }
            }
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
            when{
                expression {
                    BRANCH_NAME == 'master'
                }
            }
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
                    script {
                        sshagent(['ec2-user-key']) {
                                // some block
                                def dockercmd = 'docker run -p 3080:3080 -d shahid78600/mernapp:1.0'
                                sh "ssh -o StrictHostKeyChecking=no ec2-user@43.204.111.33 ${dockercmd}"
                            }
                    }
                }
            }
    }
}
