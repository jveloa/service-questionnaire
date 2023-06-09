pipeline {
    agent { 
        label 'master'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        gitLabConnection('GitLab-Jenkins')
    }

    triggers {
        gitlab(triggerOnPush: true, branchFilterType: 'All')
    }

    tools {
        maven 'Maven'
        jdk 'jdk8'
    }

    stages {
    
        stage("Get version Number") {
            steps {
                script {
                    env.versionNumber = sh (script: 'git describe --abbrev=0', returnStdout: true).trim()
                }
                echo "Building asset version ${env.versionNumber}"
            }
        }

        stage('Clean & Refresh') {
            steps {
                sh "mvn clean"
            }
        }
		
		
        stage('Package') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true package -Dgittag=${env.versionNumber}"
                sh "jar xf target/training-1.0.0.jar"
            }
        }

        stage('Build docker image & Publish') {
            steps {
                script {
                    docker.withTool('docker') {
                        docker.withRegistry('https://sigenu-registry.cujae.edu.cu', 'registry-credentials-id') {
                                def image = docker.build("sigenu/sigenu-training", [
                                    "-f ${env.WORKSPACE}/Dockerfile",
                                    "--build-arg JAR_FILE=./target/training-1.0.0.jar",
                                    "--build-arg SB_PROFILE=prod",
                                    "--pull",
                                    "--no-cache",
                                    "${env.WORKSPACE}",
                                ].join(' '))
                            
                            	//image.push(env.versionNumber)
                                image.push('latest')
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            updateGitlabCommitStatus name: 'build', state: 'success'
        }
        failure {
            updateGitlabCommitStatus name: 'build', state: 'failed'
        }
    }
}

//     app.push("${env.BUILD_NUMBER}")
                    //     app.push("latest")
                    // }
                    // docker.withRegistry('https://docker.io', 'dockerhub') {
                        // image.push(env.versionNumber)