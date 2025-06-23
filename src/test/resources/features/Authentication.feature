Feature: Authentication feature test

  @PriorityTinggi
  Scenario Outline: Login dengan email dan password yang valid
    Given Buka halaman login untuk pengujian login valid
    When Masukkan username "<username>" dan password "<password>" valid
    And Klik tombol login untuk login valid
    Then Pengguna berhasil masuk ke halaman Dashboard

    Examples:
      | username        | password         |
      | admin@hadir.com | MagangSQA_JC@123 |

  Scenario Outline: Login dengan email yang tidak terdaftar
    Given Buka halaman login untuk pengujian login invalid
    When Masukkan username "<username>" dan password "<password>" tidak valid
    And Klik tombol login untuk login tidak valid
    Then Pengguna akan melihat pesan error

    Examples:
      | username       | password       |
      | admiiin@hair.com | password_salah |

