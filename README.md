# MathX
Project to help youngsters learning to form valid mathematical expressions.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and test system.

### Prerequisites
You will need to install OpenJDK 11 and OpenJavaFX 11 in order to run the software.
You will need to add firebase jar files to your library

**OpenJavaFX 11**
- Download OpenJavaFX 11 from [Gluon Official Website](https://gluonhq.com/products/javafx/).
- Extract the download file in your system and remember the location.

**OpenJDK**
- Linux ```sudo apt install openjdk-11-jdk```


**IntelliJ** *(Required you want to compile, run and generate executable)*
- Install [IntelliJ](https://www.jetbrains.com/help/idea/installation-guide.html)


### Running in IntelliJ
A step by step series of examples that tell you how to get a development env running

- Clone the project
```
git clone https://github.com/SER515-Fall2019-Team4/app-mathx.git
```
- Open the project in IntelliJ

- Go to `File` &#8594; `Project Structure`
    - &#8600;`Project`
         - Set `Project SDK` to 11 
         - Set  `Project level language` to 11
         - Specify `Project compiler output` to location where you want to compile the software
    - &#8600;`Libraries`
         - Add new library `+` &#8594; `Java`  
         - Go to the file location where you extracted the OpenJavaFX and add
            
            `../javafx/lib`
    - **`Apply`**
      
      **Adding Firebase libraries**
- Download the firebase-client-android-2.5.2.jar from attachment section 
  location: https://tree.taiga.io/project/sheran-12-ser515-classscrum/wiki/firebase-docs
- Go to `File` &#8594; `Project Structure`
    
    - &#8600;`Libraries`
         - Add new library `+` &#8594; `Java`  
         - Go to the file location where you extracted the Firebase jar file and add the same
   
    -  Click on **`Apply`**
    
**Adding Firestore libraries**
- Go to `File` &#8594; `Project Structure`
    
    - &#8600;`Libraries`
         - Add new library `+` &#8594; `Java`  
         - Go to home/ of the project and add the **libs** folder
   
    -  Click on **`Apply`**


      
      
- Go to `Run` &#8594; `Edit Configuration` 
    - &#8600; `+` Add new configuration &#8594; `Application`
        - Change `Name` as you like. This will be your configuration name.
        - Set `Main Class` as `main.MainClass` 
        - Set vm-options as `--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml,javafx.web`
        
        *Replace `/path/to/javafx/lib*` with the path where you extracted the OpenJavaFX*
    - **`Apply`**
- Go to  `Run` &#8594; `Run 'ConfigurationName'` 
      
### Generating and running JAR file in IntelliJ
**Generating JAR File**
- Go to `File` &#8594; `Project Structure` 
    - &#8600; `Artifacts`
        - `+` Add new artifact &#8594; `JAR` &#8594; `From modules with dependencies`
        - Specify `Main Class` as `main.MainClass`
        - **`OK`**
    - Specify output directory
    - **`OK`**
- Go to `Build` &#8594; `Build Artifacts` &#8594; `app-mathx` &#8594; `Build`

**Running JAR File**
- Open terminal 
- Go to output directory specified when adding new artifact
```
cd app-mathx/out/artifacts/app_mathx_jar/
```
- Run the file (replace `/path/to/javafx/lib` with the path to JavaFX/lib ) 
```
java -jar --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml app-mathx.jar
```
***You may refer to another README file at https://github.com/SER515-Fall2019-Team4/app-mathx/tree/master/home/main/src/controllers for more information about the project structure***

### Authors

* **Karandeep Singh Grewal**
* **Aditya Bajaj**
* **Ria Mehta**
* **Manas Mahapatra**
* **Sheran Dass**

### Acknowledgments

* **Michael Findler**


<br/>

***Special Thanks to Mariusz Gromada for [MathParser](https://github.com/mariuszgromada/MathParser.org-mXparser)***
(Used to evaluate expressions)
