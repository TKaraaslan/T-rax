T-rax: Harcama Takip Sistemi (Expense Tracking System)
T-rax, kullanıcıların günlük harcamalarını kategorize ederek düzenli bir şekilde takip etmelerini sağlayan, Java Swing ve MySQL tabanlı bir masaüstü uygulamasıdır.

🚀 Özellikler
Harcama Kaydı: Tutar ve açıklama girerek harcamalarınızı anlık olarak kaydedin.

Kategorizasyon: Harcamalarınızı Gıda, Ulaşım, Eğlence, Eğitim ve Diğer başlıkları altında gruplandırın.

Dinamik Hesaplama: Toplam harcamanızı ve seçtiğiniz kategoriye özel harcama toplamını anlık olarak görün.

Veri Yönetimi: Geçmiş harcamalarınızı tabloda listeleyin, tek tek veya toplu olarak silin.

Otomatik Kurulum: Uygulama, gerekli veritabanı (expense_tracker) ve tabloları (expenses) ilk açılışta otomatik olarak oluşturur.

🛠️ Kurulum ve Çalıştırma
1. MySQL Bağlantı Ayarı (Zorunlu)
Projenin veritabanına bağlanabilmesi için her kullanıcının kendi yerel şifresini girmesi gerekir:

src/ExpenseTracker/SqlConnection.java dosyasını açın.

private final String PASSWORD = "YOUR_PASSWORD"; satırındaki YOUR_PASSWORD kısmını kendi MySQL root şifrenizle değiştirin. Eğer şifreniz yoksa "" (boş tırnak) bırakın.

2. "Access Denied" Hatası Çözümü
Eğer şifreniz doğru olduğu halde bağlantı hatası alıyorsanız, MySQL terminalinde (veya Workbench üzerinde) şu komutu çalıştırarak yetkilendirme yöntemini güncelleyin:

SQL

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'kendi_sifreniz';
FLUSH PRIVILEGES;
3. Eclipse Proje Aktarımı
Eclipse'te File > Import > General > Existing Projects into Workspace seçeneğini kullanın.

Proje içindeki mysql-connector-j-9.5.0.jar dosyasının Build Path > Classpath üzerinde tanımlı olduğundan emin olun.

📂 Klasör Yapısı
src/ExpenseTracker: Uygulama kaynak kodları (Main.java, SqlConnection.java).

src/src1/: Uygulama ikonları (money.png, basket.png).

src/mysql-connector/: MySQL bağlantı kütüphanesi.

📦 Teknik Gereksinimler
Java Version: 20.

Modüller: java.desktop, java.sql.

Bu proje eğitim amaçlı geliştirilmiştir.
