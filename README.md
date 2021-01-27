# issue-12972

This project is a reproducer for quarkus [issue-12972](https://github.com/quarkusio/quarkus/issues/12972)

## How to build native image

```
./gradlew clean build
```

## How to reproduce issue

Run app in a terminal window

```
docker run --rm -v $(pwd)/data:/data -e DATA_DIR=/data -p 8080:8080 quarkus/issue-12972:1.0.0-SNAPSHOT
```

Send a request to http endpoint from another terminal window

```
curl localhost:8080/test
```

You can see the following exception:

```
2021-01-27 15:15:31,747 ERROR [io.und.req.io] (executor-thread-1) Exception handling request 242c86c2-d953-479a-a748-83690970fd45-1 to /test: org.jboss.resteasy.spi.UnhandledException: java.lang.UnsupportedOperationException: Not implemented yet for GraalVM native images
	at org.jboss.resteasy.core.ExceptionHandler.handleApplicationException(ExceptionHandler.java:106)
	at org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:372)
	at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:218)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:519)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:261)
	at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:161)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:364)
	at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:164)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:247)
	at org.jboss.resteasy.plugins.server.servlet.ServletContainerDispatcher.service(ServletContainerDispatcher.java:249)
	at io.quarkus.resteasy.runtime.ResteasyFilter$ResteasyResponseWrapper.service(ResteasyFilter.java:70)
	at io.quarkus.resteasy.runtime.ResteasyFilter$ResteasyResponseWrapper.sendError(ResteasyFilter.java:76)
	at io.undertow.servlet.handlers.DefaultServlet.doGet(DefaultServlet.java:172)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:503)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:590)
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)
	at io.quarkus.resteasy.runtime.ResteasyFilter.doFilter(ResteasyFilter.java:31)
	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)
	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)
	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:63)
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:67)
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:133)
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:65)
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
	at io.undertow.security.handlers.NotificationReceiverHandler.handleRequest(NotificationReceiverHandler.java:50)
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:247)
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:56)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:111)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:108)
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
	at io.quarkus.undertow.runtime.UndertowDeploymentRecorder$9$1.call(UndertowDeploymentRecorder.java:572)
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:227)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleRequest(ServletInitialHandler.java:152)
	at io.quarkus.undertow.runtime.UndertowDeploymentRecorder$1.handleRequest(UndertowDeploymentRecorder.java:117)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:290)
	at io.undertow.server.DefaultExchangeHandler.handle(DefaultExchangeHandler.java:18)
	at io.quarkus.undertow.runtime.UndertowDeploymentRecorder$5$1.run(UndertowDeploymentRecorder.java:398)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2415)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1452)
	at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
	at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
	at java.lang.Thread.run(Thread.java:834)
	at org.jboss.threads.JBossThread.run(JBossThread.java:501)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:519)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:192)
Caused by: java.lang.UnsupportedOperationException: Not implemented yet for GraalVM native images
	at sun.java2d.cmm.lcms.LCMS.loadProfileNative(LCMS.java:13)
	at sun.java2d.cmm.lcms.LCMS.loadProfile(LCMS.java:42)
	at java.awt.color.ICC_Profile.getInstance(ICC_Profile.java:790)
	at java.awt.color.ICC_Profile.getInstance(ICC_Profile.java:1026)
	at java.awt.color.ICC_Profile.getInstance(ICC_Profile.java:991)
	at java.awt.color.ICC_Profile$2.run(ICC_Profile.java:940)
	at java.awt.color.ICC_Profile$2.run(ICC_Profile.java:936)
	at java.security.AccessController.doPrivileged(AccessController.java:84)
	at java.awt.color.ICC_Profile.getStandardProfile(ICC_Profile.java:935)
	at java.awt.color.ICC_Profile.getDeferredInstance(ICC_Profile.java:1067)
	at java.awt.color.ICC_Profile.getInstance(ICC_Profile.java:902)
	at java.awt.color.ColorSpace.getInstance(ColorSpace.java:333)
	at org.dcm4che3.image.ColorModelFactory.createMonochromeColorModel(ColorModelFactory.java:60)
	at org.dcm4che3.imageio.plugins.dcm.DicomImageReader.applyGrayscaleTransformations(DicomImageReader.java:489)
	at org.dcm4che3.imageio.plugins.dcm.DicomImageReader.read(DicomImageReader.java:468)
	at com.example.TestResource.getRenderedImage(TestResource.java:44)
	at com.example.TestResource.test(TestResource.java:32)
	at java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:170)
	at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:130)
	at org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:643)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:507)
	at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:457)
	at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:364)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:459)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:419)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:393)
	at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:68)
	at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:492)
	... 55 more 
```

You can also see if you are running in jvm mode you don't see the above exception.

To build image with jvm mode, run

```
./gradlew clean build -Dquarkus.package.type=jar -Dquarkus.native.container-build=false
```

