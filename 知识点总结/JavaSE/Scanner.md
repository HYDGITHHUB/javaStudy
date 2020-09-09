## Scanner

### 1. next()

它从遇到的第一个有效字符（非空格、非换行）开始扫描，直到遇到空格或者换行符。将这段内容以 String 返回。如果以控制台输入作为输入流传入 Scanner 的构造函数，那么这个方法会在有输入之前阻塞。

- 1、一定要读取到有效字符后才可以结束输入。
- 2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
- 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
- next() 不能得到带有空格的字符串。

```java
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入：");
		//之后我们输入  A B C D
		int matchNum = 1;
		while(scanner.hasNext()) {
			System.out.print("扫描到第" + matchNum + "段匹配内容：");
			System.out.println(scanner.next());
			matchNum++;
		}
	}

```

```java
请输入：A B C D
扫描到第1段匹配内容：A
扫描到第2段匹配内容：B
扫描到第3段匹配内容：C
扫描到第4段匹配内容：D

```

### 2. nextLine()

跟 next() 方法不同的地方在于，它是以换行符作为分割点。

- 1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
- 2、可以获得空白。

```java
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入：");
		//之后我们输入  A B C D
		int matchNum = 1;
		while(scanner.hasNextLine()) {
			System.out.print("扫描到第" + matchNum + "段匹配内容：");
			System.out.println(scanner.nextLine());
			matchNum++;
		}
	}

```

```java
请输入：A B C D
扫描到第1段匹配内容：A B C D

```

### 3. nextInt(),nextLong(),nextFloat()...

几种方法对应相应的输入类型。

跟 next() 方法很像，都是以空格或者换行符作为扫描终点。

```java
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入：");
		//之后我们输入  1 2 3 4
		int matchNum = 1;
		while(scanner.hasNextInt()) {
			System.out.print("扫描到第" + matchNum + "段匹配内容：");
			System.out.println(scanner.nextInt());
			matchNum++;
		}
	}

```

```java
请输入： 1 2 3 4
扫描到第1段匹配内容：1
扫描到第2段匹配内容：2
扫描到第3段匹配内容：3
扫描到第4段匹配内容：4

```

### 4.hasNext()

hasNext() 方法判断输入流里是否还有内容，有的话就返回 true。

```java
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入：");
		int matchNum = 1;
		while(scanner.hasNext()) {
			System.out.print("扫描到第" + matchNum +"段匹配的内容：");
			System.out.println(scanner.next());
			matchNum++;
		}
		System.out.println("能到这儿吗？");
		//do something
	}

```

```java
请输入：hasNext() will block your code!
扫描到第1段匹配的内容：hasNext()
扫描到第2段匹配的内容：will
扫描到第3段匹配的内容：block
扫描到第4段匹配的内容：your
扫描到第5段匹配的内容：code!

```

* 循环体外的打印没有执行，这就是阻塞的特性，如果你在实例化 Scanner 的时候构造函数使用的是 [System.in](http://system.in/) 作为输入流传入，那么就算读完了 hasNext() 也会一直阻塞你的代码。

> ## 解决办法：
>
> 1. 循环条件改一下，比如加点别的判断条件，保证能跳出循环。（将 while 转换为 if）
>
> 2. 在本地调试的时候，构造 Scanner 对象别使用 System.in，自己将测试数据写在一个文件里，然后利用 Scanner(File source) 这个构造函数实例化你的 Scanner 对象。这个比较麻烦，当然还是推荐第一种。
>
> 使用 File 对象不会阻塞，示例如下。
>
> 我先创建了一个测试文件在我的桌面上。文本中的内容为：hasNext() will block you code?
>
> 测试代码：
>
> ```java
> public static void main(String[] args) throws FileNotFoundException {
> 		System.out.println("使用文件 test.txt 作为输入流实例化 Scanner");
> 		System.out.println();
> 		Scanner scanner = new Scanner(new File("C:\\Users\\xudaxia0610\\Desktop\\test.txt"));
> 		int matchNum = 1;
> 		while(scanner.hasNext()) {
> 			System.out.print("扫描到第" + matchNum +"段匹配的内容：");
> 			System.out.println(scanner.next());
> 			matchNum++;
> 		}
> 		System.out.println("能到这儿吗？");
> 		//do something
> 	}
> 
> ```
>
> ```java
> 使用文件 test.txt 作为输入流实例化 Scanner
> 
> 扫描到第1段匹配的内容：hasNext()
> 扫描到第2段匹配的内容：will
> 扫描到第3段匹配的内容：block
> 扫描到第4段匹配的内容：your
> 扫描到第5段匹配的内容：code？
> 能到这儿吗？
> 
> ```
>
> 

### 5. 判断

```java
Scanner change = new Scanner(System.in);
System.out.println("请输入学生姓名：");
String studentName = change.nextLine();
System.out.println("请输入你的ID：");
String ID;
while(change.hasNext()) {
    if(change.hasNextInt()) {
        ID = change.nextLine();
        System.out.println("你的ID为：" + ID);
        break;
    } else {
        System.out.println("请输入数字！");
        ID = change.nextLine();
        continue;
    }
 }
```

```java
请输入学生姓名：
张三
请输入你的ID：
记录卡
请输入数字！
12
你的ID为：12
```

