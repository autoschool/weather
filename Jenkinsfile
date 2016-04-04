#!groovy
node('digitalocean') {
    docker.image('maven').inside('-u root') {
        stage 'Checkout'
        git credentialsId: 'b4a9fdbe-64cd-4c72-9c73-686b177c40ce', url: 'git@github.com:autoschool/weather.git'
        
        stage 'Build'
        writeFile(
            file: 'settings.xml', 
            text: "<settings><localRepository>${pwd()}/.m2repo</localRepository></settings>"
        )

        sh 'mvn -B -s settings.xml clean package'
    }
    
    docker.build "weather:${env.BUILD_TAG}", 'weather-runner' 
    
    stage 'Deploy'
    sh "docker rm -f weather || true"
    sh 'docker rmi $(docker images | grep weather | tail -n +3 | awk \'{print $3}\')'
    sh "docker run -d --name weather -it -p 80:8080 weather:${env.BUILD_TAG}"
}
