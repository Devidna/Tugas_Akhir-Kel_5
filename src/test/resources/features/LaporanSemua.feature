Feature: Laporan semua

  Background:
    Given Login


  Scenario Outline: Menampilkan data laporan semua dengan nama dan tanggal yang sesuai
    When Klik menu laporan semua
    And Masukkan nama yang sesuai "<nama>"
    And Pilih start date "<startDate>" dan end date "<endDate>" untuk memilih tanggal data laporan semua
    And Klik tombol filter untuk melakukan filter berdasarkan department
    Then Klik tombol search

    Examples:
      | nama         | startDate  | endDate    |
      | komar | Jun 20, 2025 | Jun 20, 2025 |

  Scenario Outline: Menampilkan  data laporan semua dengan tidak menuliskan nama
    When Klik menu laporan semua 02
    And Masukkan nama yang kosong "<nama>"
    And Pilih start date "<startDate>" dan end date "<endDate>" untuk memilih tanggal data laporan semua 02
    And Klik tombol filter untuk melakukan filter berdasarkan department 02
    Then Klik tombol search 02

    Examples:
      | nama         | startDate  | endDate    |
      |              | Jun 20, 2025 | Jun 20, 2025 |

  Scenario Outline: Menampilkan  data laporan semua dengan tidak memilih tanggal star date dan end date
    When Klik menu laporan semua 03
    And Masukkan nama yang sesuai "<nama>" 03
    And Klik tombol filter untuk melakukan filter berdasarkan department 03
    Then Klik tombol search 03

    Examples:
      | nama         | startDate  | endDate    |
      |   komar      |  |  |

  Scenario Outline: Menampilkan  data laporan semua dengan tidak memilih filter departement
    When Klik menu laporan semua 04
    And Masukkan nama yang sesuai "<nama>" 04
    And Pilih start date "<startDate>" dan end date "<endDate>" untuk memilih tanggal data laporan semua 04
    And Klik tombol dan kosongkan filter
    Then Klik tombol search 04

    Examples:
      | nama         | startDate  | endDate    |
      |       komar    |  Jun 20, 2025 | Jun 20, 2025 |

  Scenario Outline: Menampilkan  data laporan semua dengan tidak memasukkan nama, tidak memilih tanggal star date dan end date dan tidak memilih filter departement
    When Klik menu laporan semua 05
    And Masukkan nama kosong "<nama>"
    And Pilih start date "<startDate>" dan end date "<endDate>" dan kosongkan keduanya
    And Klik tombol dan kosongkan filter 5
    Then Klik tombol search 05

    Examples:
      | nama         | startDate  | endDate    |
      |           |  |  |