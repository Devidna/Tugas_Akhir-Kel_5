Feature: Menu Dashboard dan Pending

  Background:
    Given Admin telah login
    And Admin berada di halaman pending

  Scenario Outline: Menampilkan  data pending dengan tanggal yang sesuai
    When Admin memilih start date "<startDate>" dan "<endDate>" end date
    And Admin klik tombol filter untuk memfilter berdasarkan departemen "<department>"
    And Admin klik tombol pending search
    Then Pending data ditampilkan

    Examples:
      | department    | startDate   | endDate     |
      | AMEX MERCHANT | Jul 2, 2024 | Jul 2, 2028 |

  Scenario Outline: Menampilkan  data pending dengan tidak memilih tanggal start date dan end date
    When Admin membiarkan start date dan end date kosong
    And Admin klik tombol filter untuk memfilter berdasarkan departemen "<department>"
    And Admin klik tombol pending search
    Then Pending data tidak ditampilkan

    Examples:
      | department    |
      | AMEX MERCHANT |

  Scenario Outline: Menampilkan  data pending dengan tidak memilih filter departement
    When Admin memilih start date "<startDate>" dan "<endDate>" end date
    And Admin membiarkan filter departemen kosong
    And Admin klik tombol pending search
    Then Pending data ditampilkan

    Examples:
      | startDate   | endDate     |
      | Jul 9, 2022 | Mar 1, 2028 |

