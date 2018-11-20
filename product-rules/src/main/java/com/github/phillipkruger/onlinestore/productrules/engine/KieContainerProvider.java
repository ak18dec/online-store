package com.github.phillipkruger.onlinestore.productrules.engine;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;

@Dependent
public class KieContainerProvider {
    
    @Inject @ConfigProperty(name = "drools.dir", defaultValue = "/space/Projects/redhat/online-store/external-rules/src/main/resources/")
    private String externalFolder;
    
//    @Inject @ConfigProperty(name = "drools.url", defaultValue = "http://localhost:8180/decision-central/org.kie.workbench.drools.KIEDroolsWebapp/defaulteditor/download?path=default://master@MySpace/product-rules/src/main/resources/com/github/phillipkruger/onlinestore/productrules/product_rules/DiscountRules.drl")
//    private String externalUrl;
    
    @Produces
    public KieContainer produceKieContainer(final InjectionPoint injectionPoint) {
    
        KieServices kServices = KieServices.Factory.get();

        KieFileSystem kfs = kServices.newKieFileSystem();
        KieRepository kr = kServices.getRepository();
        
//        Resource resource = kServices.getResources().newUrlResource(externalUrl).setSourcePath("/tmp").setResourceType(ResourceType.DRL);
//        kfs.write(resource);
        
        File[] files = getDroolsFiles();
        
        for(File file:files){
            Resource resource2 = kServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
            kfs.write(resource2);
        }
        KieBuilder kb = kServices.newKieBuilder(kfs);
        kb.buildAll();
        return kServices.newKieContainer(kr.getDefaultReleaseId());
    }
    
//    private InputStream getUrlInputStream(){
//        try {
//            URL u = new URL(externalUrl);
//            return u.openStream();
//        } catch (IOException ex) {
//            return null;
//        }
//    }
    
    private File[] getDroolsFiles(){
        File dir = new File(externalFolder);
        
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isFile() && file.getName().endsWith(".drl"))return true;
                return false;
            }
        });
        return files;
    }
    
}
