# mysql-binlog-dispatch使用手册

- 前言 （最低JAVA版本为JDK1.8）

**本工具收集一些通用工具**

---

## 工程导入

```
<dependency>
    <groupId>com.github.q742972035.tools</groupId>
    <artifactId>tools</artifactId>
    <version>1.0-SNAPSHOTS</version>
</dependency>
```
**（仓库地址为maven中央仓库）**

---

## 历史版本
暂无

---

## 目前的功能
1. ThreadFactoryBuild 
> 根据名字前缀创建全局自增的工厂

**demo:**
```
CountDownLatch latch = new CountDownLatch(1);
CountDownLatch latch1 = new CountDownLatch(10);
List<Integer> list = Collections.synchronizedList(new ArrayList<>());
for (int i = 0; i < 10; i++) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                latch.await();

                String name = "testthread";
                ThreadFactory build = ThreadFactoryBuild.build(name);
                Thread thread = build.newThread(null);
                String threadName = thread.getName();
                assertThat(name).isEqualTo(threadName.split("-")[0]);
                list.add(Integer.parseInt(threadName.split("-")[1]));
                latch1.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
}


Thread.sleep(500);
latch.countDown();
latch1.await();

Collections.sort(list);

assertThat("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]").isEqualTo(list.toString());
```


2. FileUtils 
> 文件工具类
