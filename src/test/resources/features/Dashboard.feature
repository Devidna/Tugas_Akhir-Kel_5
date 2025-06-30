@dashboard
Feature: Dashboard Hadir

  @dashboardLoad @smoketest @medium @positive
  Scenario: Menampilkan menu dashboard dengan lengkap
    Given Admin login ke halaman dashboard Hadir
    When Dashboard selesai dimuat
    Then Semua elemen utama dashboard tampil dengan benar
