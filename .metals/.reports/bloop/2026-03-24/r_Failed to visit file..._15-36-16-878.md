error id: Gk94lrlGD8txCaCtSJgxJw==
### Bloop error:

Failed to visit file <WORKSPACE>/src/main/scala/main.scala~: <WORKSPACE>/src/main/scala/main.scala~
java.nio.file.NoSuchFileException: <WORKSPACE>/src/main/scala/main.scala~
	at java.base/sun.nio.fs.UnixException.translateToIOException(UnixException.java:92)
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:106)
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:111)
	at java.base/sun.nio.fs.UnixFileAttributeViews$Basic.readAttributes(UnixFileAttributeViews.java:57)
	at java.base/sun.nio.fs.UnixFileSystemProvider.readAttributes(UnixFileSystemProvider.java:161)
	at java.base/sun.nio.fs.LinuxFileSystemProvider.readAttributes(LinuxFileSystemProvider.java:99)
	at java.base/java.nio.file.Files.readAttributes(Files.java:1702)
	at java.base/java.nio.file.FileTreeWalker.getAttributes(FileTreeWalker.java:222)
	at java.base/java.nio.file.FileTreeWalker.visit(FileTreeWalker.java:268)
	at java.base/java.nio.file.FileTreeWalker.next(FileTreeWalker.java:354)
	at java.base/java.nio.file.Files.walkFileTree(Files.java:2577)
	at bloop.io.SourceHasher$.$anonfun$findAndHashSourcesInProject$3(SourceHasher.scala:112)
	at bloop.io.SourceHasher$.$anonfun$findAndHashSourcesInProject$3$adapted(SourceHasher.scala:108)
	at scala.collection.immutable.List.foreach(List.scala:431)
	at bloop.io.SourceHasher$.$anonfun$findAndHashSourcesInProject$1(SourceHasher.scala:108)
	at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:23)
	at monix.eval.internal.TaskRunLoop$.startFull(TaskRunLoop.scala:81)
	at monix.eval.internal.TaskRestartCallback.syncOnSuccess(TaskRestartCallback.scala:101)
	at monix.eval.internal.TaskRestartCallback.onSuccess(TaskRestartCallback.scala:74)
	at monix.eval.internal.TaskShift$Register$$anon$1.run(TaskShift.scala:65)
	at monix.execution.internal.InterceptRunnable.run(InterceptRunnable.scala:27)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	at java.base/java.lang.Thread.run(Thread.java:1474)
#### Short summary: 

Failed to visit file <WORKSPACE>/src/main/scala/main.scala~: <WORKSPACE>/src/main/scala/main.scala~
...