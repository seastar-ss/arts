package com.shawn.ss.compilor_test;



import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class Compilor {
    //
    String baseFolder = "/Users/shishengb18701shishengcorp.netease.com/Documents/project/webserver";
    //private String endPoint = "http://172.16.112.54:12098";

//    String baseFolder="C:\\workspace\\common-mid-plugin";
//    String baseFolder="D:\\project\\newProject\\new_code_gen\\code-analyzer\\common-mid-plugin";
//    private String endPoint="http://127.0.0.1:12098";
    public static void main(String[] args){
        new Compilor().testCompilor();
    }
//    @Test
    public void testCompilor() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
//        File baseLib=new File("D:\\project\\newProject\\new_code_gen\\code-analyzer\\common-mid-plugin\\common-script\\war\\target\\lib");
//        File baseLib=new File("C:\\workspace\\common-mid-plugin\\common-script\\war\\target\\lib");
        File baseLib = new File(baseFolder + "/lib");
        String cp = "";//""+baseFolder+"\\common-script\\war\\target\\classes;C:\\Users\\seast\\.m2\\repository\\com\\sunlands\\ss\\libs\\codeACG-plugin-server-dmo\\1.7.9.2\\codeACG-plugin-server-dmo-1.7.9.2.jar;C:\\Users\\seast\\.m2\\repository\\com\\sunlands\\ss\\libs\\codeACG-plugin-server-dmo-gen\\1.7.9.2\\codeACG-plugin-server-dmo-gen-1.7.9.2.jar;C:\\Users\\seast\\.m2\\repository\\com\\cs\\seastar\\libs\\db-common\\5.7.8\\db-common-5.7.8.jar;C:\\Users\\seast\\.m2\\repository\\com\\cs\\seastar\\libs\\db-api\\5.7.8\\db-api-5.7.8.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-orm\\5.1.6.RELEASE\\spring-orm-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\com\\cs\\seastar\\libs\\db-spring-jdbc\\5.7.8\\db-spring-jdbc-5.7.8.jar;C:\\Users\\seast\\.m2\\repository\\com\\cs\\seastar\\libs\\base-impl\\5.7.8\\base-impl-5.7.8.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\zxing\\core\\2.1\\core-2.1.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\zxing\\javase\\2.1\\javase-2.1.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\commons\\commons-imaging\\1.0-SNAPSHOT\\commons-imaging-1.0-SNAPSHOT.jar;C:\\Users\\seast\\.m2\\repository\\com\\sunlands\\ss\\libs\\gen-api\\1.7.9.2\\gen-api-1.7.9.2.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-web\\2.1.4.RELEASE\\spring-boot-starter-web-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter\\2.1.4.RELEASE\\spring-boot-starter-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-logging\\2.1.4.RELEASE\\spring-boot-starter-logging-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\logging\\log4j\\log4j-to-slf4j\\2.11.2\\log4j-to-slf4j-2.11.2.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\logging\\log4j\\log4j-api\\2.11.2\\log4j-api-2.11.2.jar;C:\\Users\\seast\\.m2\\repository\\org\\slf4j\\jul-to-slf4j\\1.7.26\\jul-to-slf4j-1.7.26.jar;C:\\Users\\seast\\.m2\\repository\\javax\\annotation\\javax.annotation-api\\1.3.2\\javax.annotation-api-1.3.2.jar;C:\\Users\\seast\\.m2\\repository\\org\\yaml\\snakeyaml\\1.23\\snakeyaml-1.23.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-json\\2.1.4.RELEASE\\spring-boot-starter-json-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\datatype\\jackson-datatype-jdk8\\2.9.8\\jackson-datatype-jdk8-2.9.8.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\datatype\\jackson-datatype-jsr310\\2.9.8\\jackson-datatype-jsr310-2.9.8.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\module\\jackson-module-parameter-names\\2.9.8\\jackson-module-parameter-names-2.9.8.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-tomcat\\2.1.4.RELEASE\\spring-boot-starter-tomcat-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-core\\9.0.17\\tomcat-embed-core-9.0.17.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-el\\9.0.17\\tomcat-embed-el-9.0.17.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-websocket\\9.0.17\\tomcat-embed-websocket-9.0.17.jar;C:\\Users\\seast\\.m2\\repository\\org\\hibernate\\validator\\hibernate-validator\\6.0.16.Final\\hibernate-validator-6.0.16.Final.jar;C:\\Users\\seast\\.m2\\repository\\javax\\validation\\validation-api\\2.0.1.Final\\validation-api-2.0.1.Final.jar;C:\\Users\\seast\\.m2\\repository\\org\\jboss\\logging\\jboss-logging\\3.3.2.Final\\jboss-logging-3.3.2.Final.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\classmate\\1.3.4\\classmate-1.3.4.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-web\\5.1.6.RELEASE\\spring-web-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-beans\\5.1.6.RELEASE\\spring-beans-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-webmvc\\5.1.6.RELEASE\\spring-webmvc-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-aop\\5.1.6.RELEASE\\spring-aop-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-context\\5.1.6.RELEASE\\spring-context-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-expression\\5.1.6.RELEASE\\spring-expression-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-core\\5.1.6.RELEASE\\spring-core-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-jcl\\5.1.6.RELEASE\\spring-jcl-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-autoconfigure\\2.1.4.RELEASE\\spring-boot-autoconfigure-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot\\2.1.4.RELEASE\\spring-boot-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-jdbc\\2.1.4.RELEASE\\spring-boot-starter-jdbc-2.1.4.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\com\\zaxxer\\HikariCP\\3.2.0\\HikariCP-3.2.0.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-jdbc\\5.1.6.RELEASE\\spring-jdbc-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-tx\\5.1.6.RELEASE\\spring-tx-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\springframework\\spring-aspects\\5.1.6.RELEASE\\spring-aspects-5.1.6.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\aspectj\\aspectjweaver\\1.9.2\\aspectjweaver-1.9.2.jar;C:\\Users\\seast\\.m2\\repository\\commons-codec\\commons-codec\\1.11\\commons-codec-1.11.jar;C:\\Users\\seast\\.m2\\repository\\commons-net\\commons-net\\3.5\\commons-net-3.5.jar;C:\\Users\\seast\\.m2\\repository\\com\\squareup\\okhttp3\\okhttp\\3.12.1\\okhttp-3.12.1.jar;C:\\Users\\seast\\.m2\\repository\\com\\squareup\\okio\\okio\\1.15.0\\okio-1.15.0.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\guava\\guava\\23.5-jre\\guava-23.5-jre.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\code\\findbugs\\jsr305\\1.3.9\\jsr305-1.3.9.jar;C:\\Users\\seast\\.m2\\repository\\org\\checkerframework\\checker-qual\\2.0.0\\checker-qual-2.0.0.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\errorprone\\error_prone_annotations\\2.0.18\\error_prone_annotations-2.0.18.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\j2objc\\j2objc-annotations\\1.1\\j2objc-annotations-1.1.jar;C:\\Users\\seast\\.m2\\repository\\org\\codehaus\\mojo\\animal-sniffer-annotations\\1.14\\animal-sniffer-annotations-1.14.jar;C:\\Users\\seast\\.m2\\repository\\com\\google\\code\\gson\\gson\\2.8.5\\gson-2.8.5.jar;C:\\Users\\seast\\.m2\\repository\\org\\redisson\\redisson\\3.9.0\\redisson-3.9.0.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-common\\4.1.30.Final\\netty-common-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-codec\\4.1.30.Final\\netty-codec-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-buffer\\4.1.30.Final\\netty-buffer-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-transport\\4.1.30.Final\\netty-transport-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-resolver\\4.1.30.Final\\netty-resolver-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-resolver-dns\\4.1.30.Final\\netty-resolver-dns-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-codec-dns\\4.1.30.Final\\netty-codec-dns-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\io\\netty\\netty-handler\\4.1.30.Final\\netty-handler-4.1.30.Final.jar;C:\\Users\\seast\\.m2\\repository\\javax\\cache\\cache-api\\1.0.0\\cache-api-1.0.0.jar;C:\\Users\\seast\\.m2\\repository\\io\\projectreactor\\reactor-core\\3.2.0.RELEASE\\reactor-core-3.2.0.RELEASE.jar;C:\\Users\\seast\\.m2\\repository\\org\\reactivestreams\\reactive-streams\\1.0.2\\reactive-streams-1.0.2.jar;C:\\Users\\seast\\.m2\\repository\\io\\reactivex\\rxjava2\\rxjava\\2.1.13\\rxjava-2.1.13.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\dataformat\\jackson-dataformat-yaml\\2.9.7\\jackson-dataformat-yaml-2.9.7.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.9.7\\jackson-core-2.9.7.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.9.7\\jackson-databind-2.9.7.jar;C:\\Users\\seast\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.9.0\\jackson-annotations-2.9.0.jar;C:\\Users\\seast\\.m2\\repository\\net\\bytebuddy\\byte-buddy\\1.8.17\\byte-buddy-1.8.17.jar;C:\\Users\\seast\\.m2\\repository\\org\\jodd\\jodd-bean\\3.7.1\\jodd-bean-3.7.1.jar;C:\\Users\\seast\\.m2\\repository\\org\\jodd\\jodd-core\\3.7.1\\jodd-core-3.7.1.jar;C:\\Users\\seast\\.m2\\repository\\mysql\\mysql-connector-java\\5.1.34\\mysql-connector-java-5.1.34.jar;C:\\Users\\seast\\.m2\\repository\\com\\sunlands\\gateway\\apigateway-sdk\\1.5-SNAPSHOT\\apigateway-sdk-1.5-SNAPSHOT.jar;C:\\Users\\seast\\.m2\\repository\\org\\apache\\commons\\commons-lang3\\3.8.1\\commons-lang3-3.8.1.jar;C:\\Users\\seast\\.m2\\repository\\com\\cs\\seastar\\libs\\base\\5.7.8\\base-5.7.8.jar;C:\\Users\\seast\\.m2\\repository\\ch\\qos\\logback\\logback-classic\\1.2.3\\logback-classic-1.2.3.jar;C:\\Users\\seast\\.m2\\repository\\ch\\qos\\logback\\logback-core\\1.2.3\\logback-core-1.2.3.jar;C:\\Users\\seast\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.5.6\\slf4j-api-1.5.6.jar";
        if (baseLib.exists()) {
            final File[] jars = baseLib.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });
            if (jars != null && jars.length > 0) {
                StringBuilder bs = new StringBuilder();
                boolean first = true;
                for (File af : jars) {
                    if (first) first = false;
                    else bs.append(":");
                    bs.append(af.getAbsolutePath());
                }
                cp = bs.toString();
            }

//            cp=StringHelper.concat(",",jars);
        }
        System.out.println(cp);
//        StringWriter writer = new StringWriter();
//        PrintWriter out = new PrintWriter(writer);
//        out.println("public class HelloWorld {");
//        out.println("  " +
//                "@com.shawn.ss.gen.api.interfaces.doc.EntryPoint\n" +
//                "public static void main(String args[]) {");
//        out.println("    System.out.println(\"This is in another java file\"+com.shawn.ss.lib.tools.CollectionHelper.class);");
//        out.println("  }");
//        out.println("}");
//        out.close();
//        JavaFileObject file = new JavaSourceFromString("HelloWorld", writer.toString());
        File output = new File("tmpclass");
        if (!output.exists() && !output.mkdirs()) {
            throw new IllegalStateException("can't create output folder");
        }

        List<String> options = CollectionHelper.<String>listBuilder()
                .add("-cp")
                .add(cp)
//                .add("-processorpath")
//                .add("D:\\project\\newProject\\new_code_gen\\new_code_gen\\maven-plugin\\compilor\\compilor-base-all-1.0.jar")
//                .add("-processor")
//                .add("com.shawn.ss.gen.GenProcessor")
                .add("-d")
                .add(output.getAbsolutePath())
//                .add("-Aconf.file=D:\\project\\newProject\\new_code_gen\\new_code_gen\\maven-plugin\\project\\")
////                .add("")
//                .add("-Asrc.base.fold=D:\\project\\newProject\\new_code_gen\\new_code_gen\\maven-plugin\\src\\main\\java")
////                .add("")
//                .add("-Agen.file.pos=com.sunlands.script_common_war.base_service")
//                .add("-Areport.url=http://172.16.112.54:12098/script/script/add")
//                .add("")
                .getList();
        final StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);

        CustomerJavaFileManager nM = new CustomerJavaFileManager(standardFileManager);
        List<File> allSrcFile = CollectionHelper.newList();
        putAllSrcFile(
                Collections.singletonList(baseFolder + "/src/java"),
                allSrcFile
        );
        final int size = allSrcFile.size();
        File[] srcFiles = allSrcFile.toArray(new File[size]);

        Iterable<? extends JavaFileObject> compilationUnits = nM.getJavaFileObjects(srcFiles);

        JavaCompiler.CompilationTask task =
                compiler.getTask(
                        new PrintWriter(System.out),
                        nM,
                        diagnostics,
                        options,
                        null,
                        compilationUnits);
        Processor lombokProcessor = null;
        try {
            Class<?> lombokClz = Class.forName("lombok.launch.AnnotationProcessorHider$AnnotationProcessor");
            lombokProcessor = (Processor) lombokClz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        List<Processor> processors = CollectionHelper.<Processor>listBuilder()
                .add(lombokProcessor)


//                .add(
//                        new lombok.launch.AnnotationProcessorHider()
//                )
                .getList();
        if (size > 0) {
            task.setProcessors(processors);
            boolean success = task.call();
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                System.out.println(diagnostic.getCode());
                System.out.println(diagnostic.getKind());
                System.out.println(diagnostic.getPosition());
                System.out.println(diagnostic.getStartPosition());
                System.out.println(diagnostic.getEndPosition());
                System.out.println(diagnostic.getSource());
                System.out.println(diagnostic.getMessage(null));

            }
            System.out.println("compile success: " + success);
        } else {
            System.out.println("no file to compile ");
        }
    }

    public static void putAllSrcFile(List<String> roots, List<File> allSrcFile) {
        if (!CollectionHelper.isCollectionEmpty(roots)) {
            for (String root : roots) {
                if (root!=null && root.length()>0) {
//                    L.info("add source file from :"+root);
                    addAllFileToList(new File(root), allSrcFile);
                }
            }
        }
    }

    private static void addAllFileToList(File src, List<File> allSrcFile) {
        if (src == null) {
            return;
        }
//        File src=new File(root);
        if (src.exists()) {
            if (src.isDirectory()) {
                File[] files = src.listFiles();
                if (files != null && files.length > 0)
                    for (File af : files) {
                        addAllFileToList(af, allSrcFile);
                    }
            } else if (src.canRead() && src.getName().endsWith(".java")) {
                allSrcFile.add(src);
            }
        }
    }
}
