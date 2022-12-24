def buildjar(){
    echo "bilding thr app...."
    sh 'mvn package'
}

def buildImage(){
    echo "bilding the image and push to repo"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'docker build -t shahid78600/my-apps-repo:1.2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push shahid78600/my-apps-repo:1.2.0"
    }
}

return this