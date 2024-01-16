package kz.bitlab.redis.middle02redis.service;

import kz.bitlab.redis.middle02redis.models.Product;
import kz.bitlab.redis.middle02redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CacheService cacheService;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        final String cacheKey = "product:" + id;
        Product cachedProduct = (Product) cacheService.getCachedObject(cacheKey);
        if (cachedProduct != null) {
            return cachedProduct;
        }
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(
                p -> cacheService.cacheObject(cacheKey, p, 1, TimeUnit.MINUTES)
        );

        return product.orElse(null);
    }

}
