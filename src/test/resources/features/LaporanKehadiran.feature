@laporanKehadiran
Feature: Laporan Kehadiran - Validasi kombinasi input

  Background:
    Given Admin login dan membuka laporan kehadiran

  @filterData @smoketest @high @positive
  Scenario Outline: Filter data laporan dengan kombinasi input valid
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tampil

    Examples:
      | name       | date1        | date2        | unit         |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      |            | Jun 19, 2025 | Jun 19, 2025 | EDC Nasional |
      | Kelompok 5 | Jun 19, 2025 | Jun 19, 2025 |              |
      |            | Jun 19, 2025 | Jun 19, 2025 |              |

  @exportData @sanitytest @medium @positive
  Scenario: Export data laporan ke file
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    Then Data laporan kehadiran tampil
    And Klik tombol export data laporan

  @lokasiData @sanitytest @low @positive
  Scenario: Klik lokasi dari kolom Lokasi Masuk
    When Input nama "Kelompok 5", tanggal "Jun 19, 2025" hingga "Jun 19, 2025", dan unit "EDC Nasional"
    Then Data laporan kehadiran tampil
    And Klik lokasi dari kolom Lokasi Masuk

  @filterData @sanitytest @medium @negative
  Scenario Outline: Filter data laporan dengan kombinasi input tidak valid
    When Input nama "<name>", tanggal "<date1>" hingga "<date2>", dan unit "<unit>"
    Then Data laporan kehadiran tidak tampil

    Examples:
      | name       | date1 | date2 | unit         |
      | Kelompok 5 |       |       | EDC Nasional |
      | Kelompok 5 |       |       |              |
      |            |       |       | EDC Nasional |
      |            |       |       |              |
