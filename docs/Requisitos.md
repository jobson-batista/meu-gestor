# Documento de Requisitos Funcionais e Não-Funcionais para MeuGestor

## 1. Introdução
Este documento define os requisitos funcionais e não funcionais para o sistema web MeuGestor, um sistema de gestão de dívidas e cobranças baseado em tecnologias Java (Spring Boot) e Angular com persistência em PostgreSQL.

---

## 2. Requisitos Funcionais

### 2.1 Gerenciamento de Dívidas
**User Story:**  
*Como* um usuário, *quero* cadastrar novas dívidas com informações como valor, data de vencimento, credor, devedor e taxa de juros (opcional) *para* manter um registro atualizado das minhas obrigações.  

**Critérios de Aceitação:**  
- Validação de campos obrigatórios (valor > 0, data de vencimento no futuro).  
- Armazenamento em tabela `debts` (PostgreSQL) com campos: `id`, `amount`, `due_date`, `creditor_id`, `debtor_id`, `status` (pending/paid), `interest_rate` (nullable).  
- Exibição de mensagem de sucesso após cadastro.  

**User Story:**  
*Como* um usuário, *quero* visualizar todas as dívidas cadastradas *para* acompanhar meu histórico.  

**Critérios de Aceitação:**  
- Lista com filtros por data, status e nome do credor/devedor.  
- Integração com API REST (Spring Boot) para recuperação de dados.  

---

### 2.2 Pagamentos
**User Story:**  
*Como* um usuário, *quero* registrar pagamentos parciais ou totais de dívidas *para* atualizar o status da dívida.  

**Critérios de Aceitação:**  
- Tabela `payments` com campos: `id`, `debt_id`, `amount_paid`, `payment_date`.  
- Atualização automática do campo `status` em `debts` (ex: "paid" se `amount_paid` >= `amount`).  
- Validação de não permitir pagamentos acima do valor da dívida.  

---

### 2.3 Relatórios
**User Story:**  
*Como* um administrador, *quero* gerar relatórios de dívidas vencidas e pagas *para* análise de desempenho.  

**Critérios de Aceitação:**  
- Exportação em PDF/Excel com filtros por período e status.  
- Uso de consultas SQL complexas para agregação de dados.  

---

### 2.4 Notificações
**User Story:**  
*Como* um usuário, *quero* receber alertas por e-mail 7 dias antes do vencimento de dívidas *para* evitar atrasos.  

**Critérios de Aceitação:**  
- Integração com serviço de e-mail (ex: JavaMail).  
- Configuração de preferências de notificação no perfil do usuário.  

---

### 2.5 Autenticação e Autorização
**User Story:**  
*Como* um usuário, *quero* fazer login no sistema *para* acessar meus dados.  

**Critérios de Aceitação:**  
- Autenticação via JWT (Spring Security).  
- Perfis de usuário: `administrator` e `user` com permissões diferenciadas.  
- Armazenamento de credenciais em tabela `users` com campos: `id`, `name`, `email`, `password` (hash SHA-256), `role`.  

**User Story:**  
*Como* um administrador, *quero* gerenciar usuários do sistema *para* controlar o acesso.  

**Critérios de Aceitação:**  
- CRUD de usuários (create, read, update, delete).  
- Validação de email único na tabela `users`.  
- Restrição de edição de perfis apenas por administradores.  

---

## 3. Requisitos Não Funcionais

### 3.1 Segurança
- Uso de HTTPS em toda a aplicação.  
- Criptografia de dados sensíveis (ex: e-mails, senhas) com algoritmo SHA-256.  
- Proteção contra SQL Injection e XSS (Spring Boot + Angular).  
- Validação rigorosa de entradas de formulários (ex: email com formato válido).  

### 3.2 Desempenho
- Tempo de resposta < 2s para requisições CRUD.  
- Escalabilidade horizontal com suporte a clusters PostgreSQL.  

### 3.3 Usabilidade
- Interface intuitiva em Angular com validação em tempo real.  
- Acessibilidade (suporte a leitores de tela).  

### 3.4 Reliabilidade
- Backup automático diário dos dados.  
- Tolerância a falhas no serviço de e-mail (fila de mensagens).  

---

## 4. Modelo de Banco de Dados (PostgreSQL)
### Main Tables
```sql
CREATE TABLE users (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) CHECK (role IN ('user', 'administrator'))
);

CREATE TABLE debts (
  id UUID PRIMARY KEY,
  amount NUMERIC NOT NULL,
  due_date DATE NOT NULL,
  creditor_id UUID REFERENCES users(id),
  debtor_id UUID REFERENCES users(id),
  status VARCHAR(20) CHECK (status IN ('pending', 'paid')),
  interest_rate NUMERIC
);

CREATE TABLE payments (
  id UUID PRIMARY KEY,
  debt_id UUID REFERENCES debts(id),
  amount_paid NUMERIC NOT NULL,
  payment_date DATE NOT NULL
);
````
