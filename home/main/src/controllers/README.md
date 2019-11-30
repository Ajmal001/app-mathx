```
controller
│
└───Grades
│   ├── GradeTwo
│   ├── GradeFive
│   └── GradeParent     (Interface)
│
│
└───Listeners
│   ├── CommonPaneListener
│   ├── SandBoxListener
│   └── SidePaneListener
│
│
│───Operator
│   ├── BinaryOperator
│   ├── CompareOperator
│   ├── EquationOperator
│   ├── ParentOperator
│   └── UnaryOperator

│
│───WorkspaceExtras
│   ├── Extractor
│   ├── NodeUtils
│   ├── ResultEvaluator
│   └── SidePaneFactory   
│ 
├── Adminpage Controller
├── Alertbox
├── Assignment Controller
├── CreateAssignmentController
├── EvaluateExpression
├── HomepageController
├── jsonFile.json
├── LoginController
├── QuestionController
├── README.md
├── ser515-team4-firebase-adminsdk-vb9rb-90250893a1.json
├── SignupController
├── ViewAssignmentController
└── WorkspaceController
```

**Grade**
* GradeParent - Interface for all the grades
* GradeTwo - Implements `GradeParent`
    * `produceWorkspace()` - produces workspace for a specific grade (two in this case)


**Listeners**
* CommonPaneListener
    * `produceQuestionPane()` 
    <br/>Creates the Question Pane
    * `produceResultPane()` 
    <br/> Creates the Result Pane
    * `produceCommonPane()`
    <br/>Set QuestionPane as the default pane
    * `switchPane()` 
    <br/>Switch between QuestionPane and ResultPane on Right Click
* SandBoxListeners
    * `makeDraggable()`
    <br/> Operator can be dragged
    * `makeJoinable()`
    <br/> Operator can be joined with other operators
    * `makeDeletable()`
    <br/> Right Click to delete the operator
* SidePaneListeners()
    * `installToolTip()`
    <br/> Show hint on hover on operator

**WorkspaceExtras**
* Extractor
    * `getAllFields(T parent)`
    <br/> Returns a HashMap <Node, Node.LocalBounds> of all the TextFields in the `parent`
    * `getExpressionDate(T parent)`
    <br/> Returns a HashMap <Node, Node.Text> of all the Textfields and Labels in `parent`
    * `getAllExpression(Pane parent)`
    <br/> Returns a HashMap <Node, Node.LocalBounds> stackpane in the `parent`
* NodeUtils
    * `formValues(T parent)`
    <br> Returns a HashMap <Node, Object> of all the TextFields, PasswordFields, TextAreas, CheckBoxes and Label in the `parent`

* ExpressionEvaluator
    * `sortByKey()`
    <br/> Returns a sorted string that can be passed to `expressionEvaluator`
    * `produceResult()`
    <br/> Fetches all the expression in the SandBox and produce result for each expression

**Workspace Controller**
* Create a `GradeParent` object according to the grade of the student
* Call `grade.produceWorkspace()`

**Adding a new grade to the software**
 - Create a new class in `Grades` Folder
 - Suppose you are making a grade - GradeX
    - It will implement the `Grades/GradeParent`
    - Create `produceWorkspace` function for your grade
    - Call `SidePaneFactory.addLabelToSidePane` for each type of operator
    - Make a string for all the operator for GradeX
    - For each operator call `SidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, operatorType(string), grade(int))`
    - Repeat the above three steps for each type of operator
    - Call `CommonPaneListeners.produceCommonPane(resultOrQuestionPane, question)`
    - `ResultOrQuestionPane.setOnMouseClicked` - call `commonPaneListeners.switchPane(resultorQuestionPane)`
 - WorkspaceController
    - make a new case in `WorkspaceController.initialize/switch(i)`
 - SidePaneFactory
    - add the `sandBoxListeners` if there is any special sandBoxListener for your grade operator

**Adding a new operator**
 - Make a new class in `Operator` Folder - ExampleOperator
 - `ExampleOperator`
    - It will implement `Operator/ParentOperator`
 - `SidePaneFactory`
    - add a switch case to `addLabelToSidePane`
    - add a switch case to `addOperatorToSidePane`
    - add the `sandBoxListeners` for your operator