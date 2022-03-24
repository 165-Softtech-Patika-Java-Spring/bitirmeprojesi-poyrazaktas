# Bitirme Projesi


<details>
  <summary>Proje Gereksinimleri</summary>

Bir marketteki ürünlerin satış fiyatlarına göre son fiyatlarını belirleyen servisin Spring Boot Framework
kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması.


## Gereksinimler:

> **Backend:**

- Kullanıcıdan kullanıcı adı, şifre, isim, soy isim bilgilerini alarak sisteme kayıt yapılır.
- Sisteme kayıtlı kullanıcılar market ürünlerinin veri girişini yapabilir.
- Ürün türlerine göre KDV oranları değişiklik göstermektedir. Bu oranlar aşağıdaki tabloda
  belirtilmiştir. __**Zaman zaman KDV oranları değişiklik gösterebilmektedir.**__

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- Ürün için veri girişi yapacak kullanıcı; ürünün adı, ürünün türü ve vergisiz satış fiyatı alanlarını
  doldurur. Her bir ürün için KDV Tutarı ve ürünün son fiyatı da hesaplanarak sisteme kaydedilir.
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullanıcı tanımlanabilir, güncellenebilir ve silinebilir.
- Sisteme yeni ürünler tanımlanabilir, güncellenebilir ve silinebilir.
- Ürünlerin fiyatları güncellenebilir.
- KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. Herhangi bir hata
  durumunda tüm işlemler geri alınmalıdır.
- Tüm ürünler listelenebilmelidir.
- Ürün türlerine göre ürünler listelenebilmelidir.
- Belirli bir fiyat aralığındaki ürünler listelenebilmelidir.
- Ürün türlerine göre aşağıdaki gibi detay veri içeren bir bilgilendirme alınabilmelidir.

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar:
- Aynı kullanıcı adı ile kullanıcı tanımı yapılamaz.
- Kullanıcı girişi kullanıcı adı & şifre kombinasyonu ile yapılır.
- Ürün türü, fiyatı, adı gibi alanlar boş olamaz.
- Ürün fiyatı sıfır ya da negatif olamaz.
- KDV oranı negatif olamaz.
> Authentication:
- Güvenli endpointler kullanınız. (jwt, bearer vs. )
> Response:
- Başarılı ve başarısız responselar için modeller tanımlayın ve bunları kullanın.
> Dökümantasyon:
- Open API Specification (Swagger tercih sebebi)
> Exception Handling:
- Hatalı işlemler için doğru hata kodlarının dönüldüğünden emin olunuz.
> Test:
- Unit ve integration testleri yazınız.

</details>

<details>
  <summary>Projenin Local Kurulumu</summary>

## Adımlar
1. pgAdmin ile application.properties'deki özelliklere göre bir veritabanı oluşturun, ya da kendi özelliklerinizle application.properties'i değiştirin.
2. Projeyi çalıştırın
3. `/swagger-ui/index.html` adresine gidin.
4. Projeye `/auth/register`ile kullanıcı kayıt edin.
5. Kayıt edilen kullanıcı bilgileri ile `/auth/login` giriş yapın ve authorization tokeninizi alın.
6. API'ya authorize olduktan sonra `/api/v1/value-added-taxes/init-vat-rates` ile bitirme projesinde tablo olarak verilen ürün tipi - kdv oranı tablosunu veritabanına ekleyin.
7. Daha sonra OPEN API' da gördüğünüz tüm istekleri deneyebilirsiniz. 
</details>


Bir marketteki satış ürünlerin ham fiyatlarına, 
ürün tipine ve KDV oranına göre satış fiyatlarını belirleyen bir servisi
Spring Boot kullanılarak gerçekleştirildi.

Projede üç adet tablo bulunmaktadır:

1. USR_USER kullanıcı tablosu
2. PRD_PRODUCT ürün tablosu
3. VAT_VALUE_ADDED_TAX KDV oranları tablosu


[![image](https://www.linkpicture.com/q/only-entities.png)](https://www.linkpicture.com/view.php?img=LPic623cf103e8bcb1493590688)
*UML diyagramı plant uml ile çizildi.*

Buradaki Ürün tablosu ve KDV tablosunun ilişkisi ProductType(*ürün tipi*) üzerinden yapılmaktadır.

Proje isterlerden "KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. 
Herhangi bir hata  durumunda tüm işlemler geri alınmalıdır." isteri, Transactional annotation'ı kullanılarak yapılmıştır.

Ürün türlerinine göre detay veri içeren tablonun oluşturulması yazılan bir dto'nun, JpaRepository üzerinden aggregation fonksiyonları içeren bir Query ile doldurulmasıyla yapılmıştır.

```java
      @Query(
        "select new com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto(" +
                "product.productType," +
                "valueAddedTax.vatRate," +
                "min(product.priceWithTax), " +
                "max(product.priceWithTax), " +
                "avg(product.priceWithTax), " +
                "count(product.id)" +
                ") " +
                "from PrdProduct product " +
                "inner join VatValueAddedTax valueAddedTax " +
                "on product.productType = valueAddedTax.productType " +
                "group by product.productType, valueAddedTax.vatRate"

)
    List<PrdProductTypeDetailDto> getAllProductTypeDetails();
```

## Test Senaryoları

Projede yazılan CalculationUtil, tüm service ve entity serviceler için Unit testler yazıldı.

| Class                         | Number of Tests | Code Coverage |
|-------------------------------|-----------------|---------------|
| CalculationUtil               | 4               | %100          |
| PrdProductService             | 18              | %100          |
| PrdProductEntityService       | 3               | %100          |
| UsrUserService                | 10              | %100          |
| UsrUserEntityService          | 2               | %100          |
| VatValueAddedTaxService       | 9               | %100          |
| VatValueAddedTaxEntityService | 2               | %100          |
| AuthenticationService         | 4               | %50           |

Yazılan senaryoları ilgili sınıfların test paketi altındaki karşılıklarından görüntüleyebilirsiniz.

## Swagger

Controller'lardaki Operation annotation'ları ile yazılan API dökümante edilmiştir.

