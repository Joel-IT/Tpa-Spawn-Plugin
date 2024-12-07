# Tpa Plugin

**Version:** 1.0-SNAPSHOT  
**API-Version:** 1.21  

## Beschreibung
Das **Tpa-Plugin** bietet ein einfaches System für Spieler, um Teleportanfragen zu senden und zu verwalten, sowie die Möglichkeit, den Spawnpunkt festzulegen und sich dorthin zu teleportieren.

---

## Features
- **Tpa-System:**
  - Spieler können Teleportanfragen an andere senden und diese annehmen oder ablehnen.
- **Spawn-System:**
  - Spieler können den Spawnpunkt setzen (mit entsprechender Berechtigung).
  - Spieler können sich jederzeit zum Spawn teleportieren.

---

## Installation
1. Lade die JAR-Datei in den `plugins`-Ordner deines Minecraft-Servers.
2. Starte den Server neu.
3. Konfiguriere die gewünschten Berechtigungen in deiner Berechtigungsverwaltung.

---

## Befehle
| Befehl         | Beschreibung                                      | Verwendung           | Standardberechtigung |
|-----------------|--------------------------------------------------|----------------------|-----------------------|
| `/tpa <player>` | Sende eine Teleportanfrage an einen anderen Spieler. | `/tpa <player>`      | Alle Spieler          |
| `/tpaccept`     | Akzeptiere eine Teleportanfrage.                 | `/tpaccept`          | Alle Spieler          |
| `/tpdeny`       | Lehne eine Teleportanfrage ab.                   | `/tpdeny`            | Alle Spieler          |
| `/spawn`        | Teleportiere dich zum gesetzten Spawnpunkt.      | `/spawn`             | Alle Spieler          |
| `/setspawn`     | Setze den aktuellen Standort als Spawnpunkt.     | `/setspawn`          | `tpa.setspawn` (OP)   |

---

## Berechtigungen
| Berechtigung       | Beschreibung                                   | Standard |
|--------------------|-----------------------------------------------|----------|
| `tpa.setspawn`     | Erlaubt es dem Spieler, den Spawnpunkt zu setzen. | OP       |

---
