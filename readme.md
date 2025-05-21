WunderList App
Wunderlist-app bir kullanıcı girişi yapabildiğimiz, farklı alanlar yaratabileceğiniz alanlar, bu alanlara görev ekleme, görevlere ait açıklamalar, alt
görevler, görevleri diğer kullanıcılarla paylaşabilme ve görevlerin bitiş sürelerini hatırlatma uygulamasıdır. Bu uygulama java 21 ile yazılmış olup
spring boot framework kullanılarak geliştirilmiştir. Uygulamada database olarak couchbase, mimari olarak domain ve busines katmanlarını birbirinden
ayırmak üzere bir temelde oluşturulmuş hexagonal architecture kullanılmıştır. Uygulama Junit ve Mockito kullanılarak test edilmiştir.

Uygulamayı Çalıştırma

Uygulamayı yerel ortamınızda çalıştırmak için aşağıdaki adımları takip ediniz:

Docker Ortamını Başlatma: Projenin kök dizininde bulunan docker-compose.yml dosyasını çalıştırın:

Bash
docker-compose up -d
Bu komut, Docker konteynerinizde "wunderlist" adında bir Couchbase konteyneri oluşturacaktır.

Couchbase Kurulumu: Konteyner oluştuktan ve Couchbase'in tam olarak başlatıldığından emin olduktan sonra (kısa bir bekleme süresi gerekebilir),
tarayıcınızdan http://localhost:8091/ adresine gidin. Burada "Setup New Cluster" seçeneğine tıklayarak yeni bir cluster oluşturun.

WunderList Bucket Oluşturma: Cluster kurulumu tamamlandıktan sonra, sol paneldeki "Buckets" kısmına gelin ve "ADD BUCKET" butonuna tıklayarak "
wunderlist-app" adında yeni bir bucket oluşturun.

Uygulamayı Başlatma: wunderlist-infra paketi altında bulunan WunderlistApplication dosyasını çalıştırın. Bu, Spring Boot uygulamasını başlatacaktır.

API Testleri: Uygulama başlatıldıktan sonra, projeyle birlikte verilen Postman Collection'ı(Postman Collection Json dosyası şekilde proje içerisinde yer almaktadır.) Postman'e import ederek uygulama uç noktalarına (
endpoints) istekler gönderebilir ve sonuçları gözlemleyebilirsiniz.

Uygulamanın Geliştirilmesi Gereken Kısımlar

-Token'lar redis cache'te saklanılabilir.

-Uygulama üzerinde todo-list kısımlarından birden fazla olduğunda görevler farklı todo-listlere aktarım yapılabilmelidir.

-Uygulamaya ait bir ön yüz yazılabilir

-Couchbase oluşturulurken otomatik bir şekilde "bucket" oluşturulmasına yarayan bir sh yaratılabilir.

-Açılan görevlere güncelleme yapılabilmesi.		