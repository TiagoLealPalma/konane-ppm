error id: B103BE2C4B946DD47941B28FB2139A15
file://<WORKSPACE>/src/main/scala/main.scala
### java.lang.StringIndexOutOfBoundsException: Index 3 out of bounds for length 3

occurred in the presentation compiler.



action parameters:
offset: 0
uri: file://<WORKSPACE>/src/main/scala/main.scala
text:
```scala
@@



```


presentation compiler configuration:
Scala version: 3.3.7-bin-nonbootstrapped
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-l5R6cozSTpqH7rOemD1rgg== [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.11.2/semanticdb-javac-0.11.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.7/scala3-library_3-3.3.7.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parallel-collections_3/1.2.0/scala-parallel-collections_3-1.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.16/scala-library-2.13.16.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>




#### Error stacktrace:

```
java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:55)
	java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:52)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:213)
	java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:210)
	java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:98)
	java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
	java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
	java.base/java.lang.String.checkIndex(String.java:4904)
	java.base/java.lang.StringLatin1.charAt(StringLatin1.java:45)
	java.base/java.lang.String.charAt(String.java:1624)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.isWhitespace$1(CommonMtagsEnrichments.scala:85)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1$$anonfun$1(CommonMtagsEnrichments.scala:89)
	scala.Option.filter(Option.scala:319)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trim$1(CommonMtagsEnrichments.scala:89)
	scala.meta.internal.mtags.CommonMtagsEnrichments$XtensionRangeParams.trimWhitespaceInRange(CommonMtagsEnrichments.scala:94)
	dotty.tools.pc.utils.InteractiveEnrichments$.sourcePosition(InteractiveEnrichments.scala:48)
	dotty.tools.pc.PcInlayHintsProvider.<init>(PcInlayHintsProvider.scala:54)
	dotty.tools.pc.ScalaPresentationCompiler.inlayHints$$anonfun$1(ScalaPresentationCompiler.scala:169)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:149)
	scala.meta.internal.pc.CompilerAccess.$anonfun$1(CompilerAccess.scala:93)
	scala.meta.internal.pc.CompilerAccess.onCompilerJobQueue$$anonfun$1(CompilerAccess.scala:210)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:153)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1474)
```
#### Short summary: 

java.lang.StringIndexOutOfBoundsException: Index 3 out of bounds for length 3