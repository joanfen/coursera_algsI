# coursera_algsI
> The practice code and homework of [Algorithms Part I of Princeton University](https://www.coursera.org/learn/algorithms-part1/home/welcome) on coursera


- 书籍：Algorithms,Fourth Edition(算法(第四版))
- 配套网站：https://algs4.cs.princeton.edu/home/

本项目主要是完成作者在 coursera 上的一门公开课上的练习和作业，还包含一些书本上的练习

### 目前进度

- week3: 进行中
- week2: Deques and Randomized Queues 88% 
- week1: Percolation 成绩 93%

## 问题汇总
### 如何输入文件参数
如果直接使用官方提供的 algs4.jar 包中的 StdIn 是无法读取到内容的，需要先将文件内容放到一个输入流中

```java
try {
    // 在 run 的时候下面的文件夹路径写上 txt 的文件路径
    // 然后在参数中填上 txt 的文件名，如 tinyUF.txt，就可以读取到文件内容了
    FileInputStream input = new FileInputStream("src/com/joanfen/week1/unionFind/" + args[0]);
    System.setIn(input);
} catch (FileNotFoundException e) {
    e.printStackTrace();
}
int N = StdIn.readInt(); // 后面就可以按照官方给的示例读取数据
...
```





