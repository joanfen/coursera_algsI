## 第三周作业
作业说明：[specification](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php)

给你平面上 n 个不同的点，找出包含了至少四个点的子集，使其连起来的线段是最大的。

作业说明里有先让我们暴力求解，再进行优化

### 解题思路记录
拿到题目第一反应就是，计算两个点的斜率，斜率相同的点便是可以连成一条线的

Point 类的实现比较简单，只需要计算斜率和进行点的大小比较，并且注释中都把条件写出来了...


然后是暴力解法，就一次给你 4 个点，你就直接四层循环，判断他们的斜率是不是相等，真的挺狠

>Write a program BruteCollinearPoints.java that examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal

非常清晰 ，你遍历四个点，判断他们是不是共线点，就看他们每两个点的斜率是不是一样的
我们 Points 里面已经实现了斜率的计算了，所以这个暴力解法还是挺简单的

后面的文档中说了，我们这个暴力解法就支持四个点，五个及以上不支持，所以，放心大胆 干就完了

下面还有边界条件和性能的说明，最差 n^4  的时间复杂度 ，就是四层循环没错
每个点的斜率进行比较，如果相等就把第一个点和最后一个点连成线

