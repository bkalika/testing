plugins { 
  java
  eclipse
}

allprojects {
   group = "it.discovery"
}

subprojects {  
   apply(plugin = "java")

   java.sourceCompatibility = JavaVersion.VERSION_23
   java.targetCompatibility = JavaVersion.VERSION_23

   repositories {
      mavenCentral()
   }
   
   dependencies {
        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
   } 
}

