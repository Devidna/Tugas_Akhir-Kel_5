@laporanKehadiran
Feature: Laporan Kehadiran - Validasi Kombinasi Filter Input

  Background:
    Given Admin login dan membuka laporan kehadiran

  @smoketest @high @positive
  Scenario Outline: Filter data laporan dengan kombinasi input yang valid
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tampil

    Examples:
      | name       | date1        | date2        | unit         |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      |            | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      | Kelompok 5 |              |              | EDC Nasional |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 |              |
      | Kelompok 5 |              |              |              |
      |            | Jun 19, 2025 | Jun 19, 2025 |              |
      |            |              |              | EDC Nasional |

  @sanitytest @medium @positive
  Scenario: Reset filter data laporan
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    And Klik tombol reset filter laporan
    Then Form filter laporan kembali kosong

  @sanitytest @medium @positive
  Scenario: Export data laporan ke file
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    And Klik tombol export data laporan
    Then Cek hasil export data laporan

  @regression @low @positive
  Scenario: Mengubah jumlah rows per page
    When Input nama "", tanggal "Jun 01, 2025" hingga "Jun 30, 2025", dan unit ""
    And Klik pagination dan pilih "5" rows
    Then Jumlah baris data yang tampil "5"

  @regression @low @positive
  Scenario: Klik lokasi dari kolom Lokasi Masuk
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    Then Data laporan kehadiran tampil
    And Klik lokasi dari kolom Lokasi Masuk

  @sanitytest @medium @negative
  Scenario Outline: Filter data laporan dengan kombinasi input tidak valid
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tidak tampil

    Examples:
      | name      | date1       | date2       | unit        |
      | Kelompok5 | Jun 19 2025 | Jun 19 2025 | EDCNasional |
      | Kelompok5 |             |             |             |
      |           | Jun 19 2025 | Jun 19 2025 |             |
      |           |             |             | EDCNasional |
      |           |             |             |             |
