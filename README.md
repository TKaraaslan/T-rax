# T-rax: Harcama Takip Sistemi (Expense Tracking System)

T-rax, kullanıcıların günlük harcamalarını kategorize ederek düzenli bir şekilde takip etmelerini sağlayan, Java Swing ve MySQL tabanlı bir masaüstü uygulamasıdır.

---

## 🚀 Özellikler

- **Harcama Kaydı:** Tutar ve açıklama girerek harcamalarınızı anlık olarak kaydedin.
- **Kategorizasyon:** Harcamalarınızı Gıda, Ulaşım, Eğlence, Eğitim ve Diğer başlıkları altında gruplandırın.
- **Dinamik Hesaplama:** Toplam harcamanızı ve seçtiğiniz kategoriye özel harcama toplamını anlık olarak görün.
- **Veri Yönetimi:** Geçmiş harcamalarınızı tabloda listeleyin, tek tek veya toplu olarak silin.
- **Otomatik Kurulum:** Uygulama, gerekli veritabanı (`expense_tracker`) ve tabloları (`expenses`) ilk açılışta otomatik olarak oluşturur.

---

## 🛠️ Kurulum ve Çalıştırma

### 1. MySQL Bağlantı Ayarı (Zorunlu)
Projenin veritabanına bağlanabilmesi için her kullanıcının kendi yerel şifresini girmesi gerekir:

- `src/ExpenseTracker/SqlConnection.java` dosyasını açın.
- `private final String PASSWORD = "YOUR_PASSWORD";` satırındaki `YOUR_PASSWORD` kısmını kendi MySQL root şifrenizle değiştirin.
- Eğer şifreniz yoksa `""` (boş tırnak) bırakın.

### 2. "Access Denied" Hatası Çözümü
Eğer şifreniz doğru olduğu halde bağlantı hatası alıyorsanız, MySQL terminalinde (veya Workbench üzerinde) şu komutu çalıştırarak yetkilendirme yöntemini güncelleyin:

```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'YOUR_PASSWORD';
FLUSH PRIVILEGES;

Not: YOUR_PASSWORD kısmına kendi şifrenizi girin.
