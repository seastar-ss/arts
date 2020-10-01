# arts 
##2019-04-08
总结若干容易触发的错误：
执行是可配置如下参数：-verbose:class -verbose:gc -XX:+PrintGCDetails
-Xmn256m -Xms256m -Xmx2G -Xss256k -XX:MaxDirectMemorySize=512M 
-XX:+UnlockCommercialFeatures  -XX:+FlightRecorder 
-XX:OnOutOfMemoryError="systeminfo &&wmic memorychip get capacity" -XX:+HeapDumpOnOutOfMemoryError 

1.循环拼接字符串
执行Main函数，输入stringConcat，会看到频繁的GC输出，时间消耗大概会超过2分钟
而输入stringBuilderConcat可以改用stringBuilder完成同样的任务，消耗在毫秒级

原理：由于string不可变，每次连接操作都会额外增加两个新对象，循环n次过程中会产生3n个对象，
而仅有一个对象是应该保留的，因而GC压力大

2.重复字符串使用字符串常量池
执行Main函数，多次输入intern , 不会出现oom错误
输入noIntern ，一次即触发 oom

原理：使用intern后，代码调用jvm将字符串存入常量池，
后续同样字符串使用intern调用，会返回常量池中的字符串引用而新建立的字符串空间可回收，因而节省了空间

针对字符串生命周期贯穿整个应用且频繁会出现的案例，此方案是一个很好的优化手段
如果字符串生命周期不长，可以考虑使用hashmap自己实现字符串常量池维护

3.无限循环案例
执行Main函数，输入intergerLoop，会发现执行到127循环停止，
但是输入intergerInfinateLoop，会发现并未在代码的256处停止，会无限循环下去

原理：java的integer存在常量池，默认范围为-128到127，此部分整数不会生成新实例，因此Integer i=1;Integer j=1;
i==j会返回true，但是超过此范围的Integer会分配新的内存，所以导致Integer i=256;Integer j=256;i==j会返回false
因为指针地址不相同, 

部分优化策略推荐增大整数常量池的范围来优化GC，减少由于整数分配新内存产生的GC压力，此方案可以尝试
使用-Djava.lang.Integer.IntegerCache.high=512可以将常量池上限扩大到512,常量池下限为-127， java8中不可调整



