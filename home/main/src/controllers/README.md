```
controller
│
└───Grades
│   ├── GradeOne
│   ├── GradeEight
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
│   ├── CounterOperator
│   ├── NumberOperator
│   ├── ParentOperator
│   └── unitUnaryOperator
│
│───workspaceCopies
│   └── WorkspaceController_Copy    (Backup)
│
│───WorkspaceExtras
│   ├── Extractor
│   ├── NodeUtils
│   ├── ResultEvaluator
│   └── SidePaneFactory   
│ 
├── Alertbox
├── Assignment Controller
├── CreateAssignmentController
├── EvaluateExpression
├── HomepageController
├── LoginController
├── Notes
├── QuestionController
├── README.md
├── ser515-team4-firebase-adminsdk-vb9rb-90250893a1.json
├── SignupController
├── ViewAssignmentController
└── WorkspaceController
```

**Grade**
* GradeParent - Interface for all the grades

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
    * `sortByKey`
    <br/> Returns a sorted string that can be passed to `expressionEvaluator`
    * `evaluateResult`
    <br/> Fetches all the expression in the SandBox and produce result for each expression

**Workspace Controller**
* Create a `grade` object
* Call `grade.produceWorkspace`