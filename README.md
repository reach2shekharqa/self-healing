# Self Healing Selenium Core

A Selenium automation framework that automatically heals broken locators.

## Features

- Automatic locator recovery
- DOM based element intelligence
- Fingerprint matching
- Selenium WebDriver wrapper

## Usage

```xml
<dependency>
    <groupId>com.selfhealing</groupId>
    <artifactId>self-healing-core</artifactId>
    <version>1.0.0</version>
</dependency>

WebDriver driver = new ChromeDriver();

driver = HealingDriver.create(driver);


---

