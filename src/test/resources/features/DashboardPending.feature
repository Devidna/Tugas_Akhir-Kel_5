@dashboard
Feature: Menu Dashboard dan Pending

  @smoketest @medium @positive
  Scenario: Menampilkan menu dashboard dengan lengkap
    Given Admin login ke halaman dashboard Hadir
    When Dashboard selesai dimuat
    Then Semua elemen utama dashboard tampil dengan benar

  @smoketest @high @positive
  Scenario Outline: Menampilkan  data pending dengan tanggal yang sesuai
    Given Admin telah login dan buka halaman pending
    When Admin memilih start date "<startDate>" dan "<endDate>" end date
    And Admin klik tombol filter untuk memfilter berdasarkan departemen "<department>"
    And Admin klik tombol pending search
    Then Pending data ditampilkan

    Examples:
      | department    | startDate   | endDate     |
      | AMEX MERCHANT | Jul 2, 2024 | Jul 2, 2028 |

  @sanitytest @medium @positive
  Scenario Outline: Menampilkan  data pending dengan tidak memilih filter departement
    Given Admin telah login dan buka halaman pending
    When Admin memilih start date "<startDate>" dan "<endDate>" end date
    And Admin membiarkan filter departemen kosong
    And Admin klik tombol pending search
    Then Pending data ditampilkan

    Examples:
      | startDate   | endDate     |
      | Jul 9, 2022 | Mar 1, 2028 |

  @sanitytest @medium @negative
  Scenario Outline: Menampilkan  data pending dengan tidak memilih tanggal start date dan end date
    Given Admin telah login dan buka halaman pending
    When Admin membiarkan start date dan end date kosong
    And Admin klik tombol filter untuk memfilter berdasarkan departemen "<department>"
    And Admin klik tombol pending search
    Then Pending data tidak ditampilkan

    Examples:
      | department    |
      | AMEX MERCHANT |