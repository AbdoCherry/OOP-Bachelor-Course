# First Week of learning Java programming language

In this week we will get in touch with easy functionalities of the programming
language java is offering.
Some examples will contain few advanced techniques to simplify some outputs.

Nevertheless you shouldn´t struggle if you find any complicated steps. On many
sources you´ll find support and good descriptions, even better explained than
here. And keep in mind, that no matter which problem you are facing or issue you
are struggling, many others before you had the problem also. Don´t get less
motivated if it becomes harder. If it would be easy, everyone would do it then.

--- 

## <u>Task 1:</u>

This task is not included in the source folder. The task it self is to create a
workspace of your work and if you already started your <b>I</b>ntegrated <b>
D</b>evelopment <b>E</b>nvironment <b>(IDE)</b> and created a project. If you
are using Code Editors like Visual Studio
Code <a href="https://code.visualstudio.com"></a><b></b> you can directly start
after installing the necessary Java extension. Here is a list of famous and
widely used IDE´s

- <a href="https://www.eclipse.org/ide/"><b>Eclipse</b></a><br>
- <a href="https://www.jetbrains.com/de-de/idea/"><b>IntelliJ IDEA</b></a>

## <u>Task 2:</u>

<p>This task makes you familiar with the interaction with java. The requirements are to print your name, surname and e-mail address in three different lines.</p>
To be able to print out some output on your console you have to use the 'print' or println function inside a <b>main method</b>. Don´t forget that java is, like explained in the readme document, is an object oriented programming language. Therefore every *.java File is a class. And the first lines of code of this class usually starts with</p>

```
    public class MyFirstClass{
        public static void main(String[] args){
            /
            *
            * Your code here
            */
        }
    }
```

<p>One more information to know about the printline functions, they are a few types of them. All of them belong to the <b>PrintStream</b> Class. Let´s see what those three have to offer for us.</p>
<p><ul>
    <li><b>System.out.print()</b></li>
        <ul>
            <li>The curser remains at the same position of the last character of the output</li>
            <li>It it the basic method to return text to the console</li>
        </ul>
    <li><b>System.out.println()</b></li>
        <ul>
            <li>Everything in the brackets will be printed and then a new line starts</li>
            <li>It is an overloaded function of the PrintStream class</li>
        </ul>
    <li><b>System.out.printf()</b></li>
        <ul>
            <li>Is used when output needs to be manipulated, or lets say formatted</li>
            <li>Formats requires specific pattern to adress the compile how to interpret parameters inside the brackets</li>
        </ul>
</ul></p>

Don´t forget, any code is only executable if is inside a main method. We will
get later to classes without the main method inside of it. But for the beginning
we will stay with single class tasks.

## <u>Task 3:</u>

<p>Now we go one step further. After we know how to return hard coded output to the console, we want to use some simple mathematical operations to define a result. Also we want to set up an Input - Output <b>(I/O)</b> Interaction between application and user</p>
<p>With the <b>System.out.println()</b> or <b>System.out.print()</b> we can print out text to the console and print a desired result.
Now we want to input something through the console. In other words we let the user enter something into the program.To make this possible we use the class <b>Scanner</b> as followed with the code snippet.</p>

````
public class MyFirstClass{
    public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in); // Declaring variable of complex type 'Scanner'
        String myName = myScanner.nextLine(); // Declaring variable which takes our input with scanner
    }
}
````

<p>One additional functionality on declaring classes, variables and methods are the modifiers. They support you in coding a more stable and high-functioning.
<br>Declaring the modifier <b><u>final</u></b> before a variable defines the value of the initialized as fixed constant</p>

````
final double EXCHANGERATE = 1.1567; // The value is fixed and not called immutable for the whole program. 
````

<p>Then asking the user to enter an amount through his terminal with support of the <b><u>Scanner</u></b> Class and multiplying it with the exchange rate to get the dollar value<br>
Check the source code of task 3 to comprehend the way of problem solving</p>

## <u>Task 4:</u>
<p>In this task no new elements of java are added. You can check the formatting output again to print the results in a better view<br>
Keep in mind, that a full-stack application has also <a href = "https://www.frontend-gmbh.de/en/blog/what-is-frontend-what-is-backend/"><strong>Frontend</strong></a> to represent the interaction in a more user friendlier way</p>

## <u>Task 5:</u>
<p>Same calculation as task 3 and 4</p>




 
 







