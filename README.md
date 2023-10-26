
# Java Mail Service Modulü
JavaMail API'si ile yapabileceğiniz bazı temel işlemler:

- E-posta mesajı oluşturma ve gönderme.
- E-posta konusunu, gövdesini ve alıcılarını ayarlamak.

## SMTP (Simple Mail Transfer Protocol)

elektronik postaların iletilmesi ve yönlendirilmesi için kullanılan bir iletişim protokolüdür. SMTP'nin temel amacı, e-postaların güvenilir bir şekilde iletilmesini sağlamak ve e-posta iletişimi için standart bir yol sağlamaktır. SMTP, e-posta gönderme işlemlerinin alt yapısını oluşturur ve elektronik postaların hızlı ve güvenilir bir şekilde iletilmesini sağlar.
## Kurulum

**Adım 1: Postfix'i Kurma**
Postfix'i kurarak ve Gmail üzerinden e-posta göndermek için  adım adım nasıl yapılacağını açıklamaktadır. Bu işlem, bir Ubuntu sunucusu üzerinde gerçekleştirilecektir.
Postfix'i kurmak için aşağıdaki komutu kullanın:

```
sudo apt install postfix
```
Kurulum sırasında, "Internet Site" seçeneğini seçin.

**Adım 2: Postfix Yapılandırma**

Postfix yapılandırma dosyasını düzenlemek için aşağıdaki komutu kullanın:

```
sudo nano /etc/postfix/main.cf
```
Daha sonra aşağıdaki satırları ekleyin:

```
shellCopy code
smtpd_banner = $myhostname ESMTP $mail_name (Ubuntu)
biff = no
append_dot_mydomain = no
readme_directory = no
compatibility_level = 3.6

smtpd_tls_cert_file=/etc/ssl/certs/ssl-cert-snakeoil.pem
smtpd_tls_key_file=/etc/ssl/private/ssl-cert-snakeoil.key
smtpd_tls_security_level=may

# Aşağıdaki satırları ekleyin:
relayhost = [smtp.gmail.com]:587
smtp_tls_CApath = /etc/ssl/certs
smtp_tls_security_level = encrypt
smtp_sasl_auth_enable = yes
smtp_sasl_password_maps = hash:/etc/postfix/sasl/sasl_passwd
smtp_sasl_security_options = noanonymous

```
**Adım 3: SMTP Kimlik Bilgileri Eklemek**

SMTP sunucusuna erişmek için Gmail kimlik bilgilerinizi eklemek için aşağıdaki komutları kullanın:

```
echo "[smtp.gmail.com]:587 your-email:password" | sudo tee -a /etc/postfix/sasl/sasl_passw
```

Daha sonra aşağıdaki komutları kullanarak dosyanın izinlerini ayarlayın:

```
sudo postmap /etc/postfix/sasl/sasl_passwd
sudo chown root:postfix /etc/postfix/sasl/sasl_passwd*
sudo chmod 640 /etc/postfix/sasl/sasl_passwd*
```
**Adım 4: Hostname Ayarlama**

Aşağıdaki komutu kullanarak sunucunuzun hostname'inizi öğrenin:

```
hostname
```

Aşağıdaki komutu kullanarak sunucunuzun hostname'ini belirleyin:

```
sudo hostname <hostname> zynpzynp.uygun@gmail.com
```

**Adım 5: Postfix Servisini Yeniden Başlatma**

Postfix servisini yeniden başlatmak için aşağıdaki komutu kullanın:

```
sudo service postfix restart
```

**Adım 6: Test Mesajı Gönderme**

Aşağıdaki komutu kullanarak test bir mesaj gönderin:

```
echo "Bu bir test mesajıdır." | mail -s "Test Mesajı" <alıcı maili>
```

Artık Postfix ile Gmail üzerinden e-posta göndermeye hazırsınız.

**Adım 7: application.properties dosyası değişiklikleri**

```
email.username=your-email
email.password=password
```
Username ve password alanlarını güncellemeyi unutmayınız.

