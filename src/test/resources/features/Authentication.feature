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
      | admiiin@hair.com | MagangSQA_JC@123 |


  Scenario Outline: Login dengan email yang tidak menggunakan simbol "@"
    Given Buka halaman login untuk pengujian login invalid 02
    When Masukkan username "<username>" dan password "<password>" tidak valid 02
    And Klik tombol login untuk login tidak valid 02
    Then Pengguna akan melihat pesan error validasi email

    Examples:
      | username       | password       |
      | adminhair.com | MagangSQA_JC@123 |


  Scenario Outline: Login dengan password yang salah
    Given Buka halaman login untuk pengujian login invalid 03
    When Masukkan username valid "<username>" dan password salah "<password>"
    And Klik tombol login untuk login tidak valid 03
    Then Pengguna akan melihat pesan error 03

    Examples:
      | username       | password       |
      | admin@hadir.com | password_salah |

  Scenario Outline: Login dengan password kosong
    Given Buka halaman login untuk pengujian login invalid 04
    When Masukkan username valid "<username>" dan kosongkan password "<password>"
    And Klik tombol login untuk login tidak valid 04
    Then Pengguna akan melihat pesan error 04

    Examples:
      | username       | password       |
      | admin@hadir.com |  |

  Scenario Outline: Login dengan email kosong
    Given Buka halaman login untuk pengujian login invalid 05
    When Masukkan username kosong "<username>" dan password valid "<password>"
    And Klik tombol login untuk login tidak valid 05
    Then Pengguna akan melihat pesan error 05

    Examples:
      | username       | password       |
      |  | MagangSQA_JC@123 |

  Scenario Outline: Login dengan email dan password kosong
    Given Buka halaman login untuk pengujian login invalid 06
    When Masukkan username kosong "<username>" dan password kosong "<password>"
    And Klik tombol login untuk login tidak valid 06
    Then Pengguna akan melihat pesan error 06

    Examples:
      | username       | password       |
      |  |  |

  Scenario Outline: Verifikasi Log out akun
    Given Buka halaman login
    When Masukkan username "<username>" dan password "<password>"  valid
    And Klik tombol login untuk login  valid
    And Klik user profile pada bagian navbar kanan atas halaman
    Then Klik Logout di dalam navbar

    Examples:
      | username        | password         |
      | admin@hadir.com | MagangSQA_JC@123 |

