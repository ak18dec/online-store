package com.github.phillipkruger.onlinestore.catalogservice.data;

import com.github.phillipkruger.onlinestore.catalogservice.Product;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.imageio.ImageIO;
import lombok.Getter;

@ApplicationScoped
public class ProductRepository {
    @Getter
    private final Map<String, Product> catalog = new HashMap<>();
    @Getter
    private final Map<String, byte[]> images = new HashMap<>();
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        
        Product anvils = buildProduct("Anvils","Best wrought anvils","anvils","399.99");
        catalog.put(anvils.getId(),anvils);
        images.put(anvils.getImageName(), getLogo(anvils.getImageName()));
        
        Product dehydratedBoulders = buildProduct("Dehydrated Boulders","Just add water","dehydrated_boulders","99.99");
        catalog.put(dehydratedBoulders.getId(),dehydratedBoulders);
        images.put(dehydratedBoulders.getImageName(), getLogo(dehydratedBoulders.getImageName()));
        
        Product dynamite = buildProduct("Dynamite","It goes boom","dynamite","500.00");
        catalog.put(dynamite.getId(),dynamite);
        images.put(dynamite.getImageName(), getLogo(dynamite.getImageName()));
        
        Product earthquakePills = buildProduct("Earthquake Pills","Just take two","earthquake_pills","49.99");
        catalog.put(earthquakePills.getId(),earthquakePills);
        images.put(earthquakePills.getImageName(), getLogo(earthquakePills.getImageName()));
        
        Product magnet = buildProduct("Magnet","North vs South","magnet","124.59");
        catalog.put(magnet.getId(),magnet);
        images.put(magnet.getImageName(), getLogo(magnet.getImageName()));
        
        Product unicycle = buildProduct("Unicycle","Powered by a rocket !","unicycle","999.99");
        catalog.put(unicycle.getId(),unicycle);
        images.put(unicycle.getImageName(), getLogo(unicycle.getImageName()));
        
        Product hispeedTonic = buildProduct("Hi-speed Tonic","Become fast like flash","hi_speed_tonic","1199.99");
        catalog.put(hispeedTonic.getId(),hispeedTonic);
        images.put(hispeedTonic.getImageName(), getLogo(hispeedTonic.getImageName()));
        
        Product tornadoKit = buildProduct("tornado_kit","Home made tornados","tornado_kit","599.99");
        catalog.put(tornadoKit.getId(),tornadoKit);
        images.put(tornadoKit.getImageName(), getLogo(tornadoKit.getImageName()));
        
    }
 
    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        catalog.clear();
    }
 
    private Product buildProduct(String name, String description,String imageName, String price){
        String id = name.toLowerCase().replaceAll(" ", "");
        return Product.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .imageName(imageName)
                        .price(new Double(price)).build();
    }
    
    private byte[] getLogo(String name){
        try(InputStream logo = this.getClass().getClassLoader().getResourceAsStream("META-INF/resources/images/" + name + ".png")){
            BufferedImage bufferedImage = ImageIO.read(logo);
            BufferedImage resizedImage = resizeImage(100, bufferedImage);
            try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                ImageIO.write(resizedImage, "png", baos);
                return baos.toByteArray();
            }
        } catch (IOException ex) {
            return null;
        }
    }
    
    private BufferedImage resizeImage(int size,BufferedImage originalImage){
	BufferedImage resizedImage = new BufferedImage(size, size, originalImage.getType());
	       Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, size, size, null);
	g.dispose();

	return resizedImage;
    }
    
}
