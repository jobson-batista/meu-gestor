# 📑 MeuGestor - Sistema de Gestão de Dívidas e Cobranças

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

O **MeuGestor** é uma solução completa para o controle financeiro de obrigações, permitindo o cadastro de dívidas, acompanhamento de pagamentos e geração de relatórios estratégicos. Desenvolvido com foco em segurança e usabilidade, o sistema atende tanto usuários comuns quanto administradores.

---

## 🚀 Funcionalidades Principais

### 💰 Gestão de Dívidas
* **Cadastro Inteligente:** Registro de valores, datas de vencimento, credores/devedores e taxas de juros.
* **Acompanhamento:** Visualização de histórico com filtros avançados por status e datas.
* **Pagamentos:** Registro de amortizações parciais ou liquidação total com atualização automática de status.

### 📊 Inteligência e Relatórios
* **Exportação:** Geração de relatórios em PDF e Excel para análise de desempenho e dívidas vencidas.
* **Notificações:** Alertas automáticos por e-mail 7 dias antes do vencimento.

### 🔐 Segurança e Acesso
* **Autenticação JWT:** Login seguro com diferentes níveis de permissão (`Admin` e `User`).
* **Proteção de Dados:** Criptografia de senhas (SHA-256) e proteção contra SQL Injection/XSS.

---

## 🛠️ Stack Tecnológica

* **Backend:** Java 17+ com Spring Boot
* **Frontend:** Angular (SPA) com validações em tempo real
* **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security + JWT
* **Comunicação:** API RESTful

---

## 🗄️ Estrutura do Banco de Dados

O sistema utiliza um modelo relacional robusto no PostgreSQL:

* `users`: Gestão de perfis e credenciais.
* `debts`: Registro detalhado das obrigações financeiras.
* `payments`: Histórico de transações vinculadas às dívidas.

---

## 📋 Requisitos do Sistema

### Funcionais (Principais)
- [ ] CRUD de Dívidas e Usuários.
- [ ] Sistema de permissões diferenciadas.
- [ ] Validação de pagamentos (não permite valor maior que a dívida).
- [ ] Disparo de e-mails via JavaMail.

### Não Funcionais
- [ ] Tempo de resposta < 2s.
- [ ] Interface acessível e responsiva.
- [ ] Backup diário automático.
- [ ] Conexão segura via HTTPS.

---
