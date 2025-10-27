# 🏦 Banking Application

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**A modern, feature-rich desktop banking system built with JavaFX**

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [Architecture](#-architecture) • [Demo](#-demo)

</div>

---

## 📋 Overview

Welcome to the **Banking Application** — a comprehensive desktop banking system that brings the power of modern banking to your fingertips! Built with JavaFX and designed with both customers and managers in mind, this application demonstrates clean code architecture, object-oriented design principles, and an intuitive user interface.

Whether you're managing customer accounts or performing everyday banking operations, this application has got you covered with a sleek, user-friendly interface and robust functionality.

## ✨ Features

### 👥 Dual User System

#### **Manager Portal**
- 🔐 Secure authentication system
- ➕ Add new customer accounts
- 🗑️ Delete existing customers
- 📊 Complete account management control

#### **Customer Portal**
- 💰 View current balance in real-time
- 💵 Deposit money
- 🏧 Withdraw funds
- 🛒 Make online purchases (minimum $50)
- 🎖️ Dynamic membership level system

### 🎖️ Tiered Membership System

The application features a sophisticated three-tier membership system that automatically updates based on account balance:

| Level | Balance Range | Online Purchase Fee |
|-------|--------------|---------------------|
| 🥈 **Silver** | < $10,000 | $20 |
| 🥇 **Gold** | $10,000 - $19,999 | $10 |
| 💎 **Platinum** | ≥ $20,000 | **FREE** |

Your membership level automatically upgrades or downgrades with each transaction — the more you save, the more you benefit!

## 🚀 Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- JavaFX SDK (if using JDK 11+)
- Your favorite IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.)

### Setup Steps

1. **Clone the repository**
git clone https://github.com/bilxl-irfan/banking-application.git
cd banking-application

2. **Configure JavaFX** (if using JDK 11+)
- Download JavaFX SDK from [OpenJFX](https://openjfx.io/)
- Add JavaFX libraries to your project build path

3. **Compile and Run**
javac -d bin src/coe528/project/*.java
java -cp bin coe528.project.Main

## 💻 Usage

### Getting Started

1. **Launch the Application**
- Run `Main.java` to start the banking system
- You'll be greeted with a welcoming home screen

2. **Choose Your Role**
- **Manager**: Access administrative functions
- **Customer**: Perform banking transactions

### Manager Quick Start

Default Manager Credentials:
Username: admin
Password: password

- Add customers with custom usernames and passwords
- All new customers start with a $100 balance at Silver level
- Delete customer accounts when needed

### Customer Quick Start

- Log in with your credentials
- View your current balance and membership level
- Make deposits, withdrawals, or online purchases
- Watch your membership level automatically update!

## 🏗️ Architecture

This application follows **SOLID principles** and clean code architecture:

coe528.project/
├── Main.java # JavaFX Application & UI Controller
├── User.java # Abstract base class for all users
├── Customer.java # Customer entity with banking operations
├── Manager.java # Manager entity with admin operations
├── BankAccount.java # Account balance management
├── Level.java # Abstract membership level
├── SilverLevel.java # Silver tier implementation
├── GoldLevel.java # Gold tier implementation
└── PlatinumLevel.java # Platinum tier implementation

### Design Patterns Used

- **State Pattern**: Membership levels (Silver, Gold, Platinum) dynamically change based on account balance
- **Inheritance**: User hierarchy with Manager and Customer subclasses
- **Encapsulation**: BankAccount manages balance operations independently
- **File Persistence**: Customer data stored in text files for data persistence

### Key Design Highlights

- **Abstraction Functions**: Well-documented representation invariants
- **Mutable Objects**: Customers with changeable states (balance, level)
- **Error Handling**: Comprehensive exception handling with user-friendly messages
- **Input Validation**: Protects against invalid transactions

## 🎨 Demo

### Welcome Screen
Start your banking journey with an elegant welcome interface.

### Manager Dashboard
Effortlessly manage customer accounts with intuitive controls.

### Customer Dashboard
A clean, modern interface for all your banking needs.

### Real-Time Level Updates
Watch your membership tier upgrade as your balance grows!

## 🛡️ Security Features

- Password-protected authentication
- File-based user credential storage
- Input validation on all transactions
- Insufficient balance protection
- Minimum purchase amount enforcement

## 📝 Technical Details

### Transaction Rules

- **Deposits**: Any positive amount accepted
- **Withdrawals**: Must not exceed current balance
- **Online Purchases**: 
  - Minimum amount: $50
  - Includes tier-based fee
  - Total charge must not exceed balance

### Data Persistence

Customer information is automatically saved to individual text files:
customerfiles/
└── [username].txt
├── Username: [username]
├── Password: [password]
├── Role: customer
├── Balance: [amount]
└── Level: [Silver/Gold/Platinum]

## 🎯 Future Enhancements

- Transaction history tracking
- Account statements and reports
- Inter-customer fund transfers
- Password encryption
- Database integration
- Mobile responsive design

## 👨‍💻 Author

**Bilal Irfan** | Student ID: 501176502

## 📄 License

This project is licensed under the MIT License - feel free to use it for learning and development purposes!

## 🙏 Acknowledgments

Built as a software engineering project demonstrating object-oriented design principles, clean code architecture, and JavaFX GUI development.

---

<div align="center">

**⭐ Star this repo if you found it helpful!**

Made with ☕ and 💻 by [Bilal Irfan](https://github.com/bilxl-irfan)

</div>
