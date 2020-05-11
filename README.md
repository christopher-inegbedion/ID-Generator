# **ID generator**
Used to generate a random ID at a set length, using a SHA1 algorithm to generate a task ID from the task action and execution code

### Creating an ID

An IDGenerator constructor takes in the task action and execution mode as parameters. There are 3 types of execution modes, namely:
1. delivery
2. meet_up_p
3. meet_up_c

```java
String task_action = "deliver food";
String execution_mode = "delivery";

IDGenerator idGenerator = new IDGenerator(task_action, execution_mode);
System.out.println(idGenerator.constructID()); //produces 157e48eac3-1 as output
```

