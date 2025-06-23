@regression @laporanKehadiran @positive
Feature: Laporan Kehadiran - Validasi kombinasi input

  Background:
    Given Admin login dan membuka laporan kehadiran

  @LPRK-WEB-001 @filterData
  Scenario Outline: Filter data laporan dengan kombinasi input yang berbeda
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tampil

    Examples:
      | name       | date1        | date2        | unit         |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      |            | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 |              |
      |            | Jun 19, 2025 | Jun 19, 2025 |              |

  @LPRK-WEB-002 @exportData
  Scenario: Export data laporan
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    Then Data laporan kehadiran tampil
    And Klik tombol export data laporan

  @LPRK-WEB-003 @klikLokasi
  Scenario: Klik lokasi dari data laporan
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    Then Data laporan kehadiran tampil
    And Klik lokasi dari kolom Lokasi Masuk

  @LPRK-WEB-004 @negative
  Scenario Outline: Filter data laporan negatif case
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tidak tampil

    Examples:
      | name       | date1 | date2 | unit         |
      | Kelompok 5 |       |       | EDC Nasional |
      |            |       |       |              |
      | Kelompok 5 |       |       |              |
      |            |       |       | EDC Nasional |