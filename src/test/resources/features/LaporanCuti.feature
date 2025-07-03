@laporanCuti
Feature: Laporan Cuti

  Background:
    Given Admin telah login dan buka halaman laporan cuti

  Scenario Outline: Menampilkan data laporan Cuti dengan nama dan tanggal yang sesuai
    When Admin memasukkan nama "<name>" yang sesuai
    And Admin memilih tanggal start date "<startDate>" dan end date "<endDate>"
    And Admin memilih filter department "<department>"
    And Admin klik tombol search
    Then Data laporan cuti berhasil ditampilkan

    Examples:
      | name           | startDate    | endDate      | department |
      | Hadir          | Jul 9, 2022  | Mar 1, 2028  | Sysmex     |


  Scenario Outline: Menampilkan data laporan Cuti dengan tidak menuliskan nama
    When Admin mengosongkan input nama
    And Admin memilih tanggal start date "<startDate>" dan end date "<endDate>"
    And Admin memilih filter department "<department>"
    And Admin klik tombol search
    Then Data laporan cuti berhasil ditampilkan

    Examples:
      | startDate   | endDate      | department |
      | Jul 9, 2022 | Mar 1, 2025  | Sysmex     |

  Scenario Outline: Menampilkan data laporan Cuti dengan tidak memilih tanggal start date dan end date
    When Admin memasukkan nama "<name>" yang sesuai
    And Admin mengosongkan tanggal start date dan end date
    And Admin memilih filter department "<department>"
    And Admin klik tombol search
    Then Data laporan cuti berhasil ditampilkan

    Examples:
      | name           | department |
      | Hadir          | Sysmex     |

  Scenario Outline: Menampilkan data laporan Cuti dengan tidak memilih filter department
    When Admin memasukkan nama "<name>" yang sesuai
    And Admin memilih tanggal start date "<startDate>" dan end date "<endDate>"
    And Admin mengosongkan filter department
    And Admin klik tombol search
    Then Data laporan cuti berhasil ditampilkan

    Examples:
      | name             | startDate   | endDate      |
      | Hadir            | Jul 9, 2022 | Mar 1, 2028  |

  Scenario: Menampilkan data laporan Cuti dengan tidak memasukkan nama, tidak memilih tanggal start date dan end date dan tidak memilih filter department
    When Admin mengosongkan input nama
    And Admin mengosongkan tanggal start date dan end date
    And Admin mengosongkan filter department
    And Admin klik tombol search
    Then Data laporan cuti berhasil ditampilkan