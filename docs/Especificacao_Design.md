# Especificação de Design - MeuGestor

## 1. Paleta de Cores
### 1.1 Cores Primárias
- **Azul Confiança**: `#0F172A` (hex) / `hsl(215, 20%, 10%)` (HSL)  
  - Usado para elementos de ação e bordas de foco.
- **Azul Escuro**: `#1E293B` (hex) / `hsl(215, 10%, 15%)` (HSL)  
  - Fundo de componentes principais e textos secundários.

### 1.2 Cores Secundárias
- **Teal Moderno**: `#0EA5E9` (hex) / `hsl(195, 100%, 50%)` (HSL)  
  - Usado para botões de ação e destaque em gráficos.
- **Amarelo Alerta**: `#F59E0B` (hex) / `hsl(40, 100%, 50%)` (HSL)  
  - Para notificações de alerta e status pendente.

### 1.3 Neutros
- **Branco**: `#FFFFFF` (hex) / `hsl(0, 0%, 100%)` (HSL)  
  - Fundo de telas e backgrounds.
- **Cinza 100**: `#F7FAFC` (hex) / `hsl(0, 0%, 98%)` (HSL)  
  - Fundo de cards e separadores.
- **Cinza 700**: `#475569` (hex) / `hsl(215, 10%, 30%)` (HSL)  
  - Textos de baixo contraste (ex: labels).

### 1.4 Contraste de Acessibilidade
- Todos os textos seguem a proporção de contraste **4.5:1** com fundos (WCAG AA).
- Textos em destaque (ex: títulos) seguem **7:1**.

---

## 2. Tipografia
### 2.1 Fontes
- **Primária**: `Inter` (Google Fonts)  
  - Suporta idiomas múltiplos e tempes de leitura fluidos.
- **Secundária**: `Roboto` (Google Fonts)  
  - Usada para elementos de interface (ex: botões).

### 2.2 Escala Tipográfica
| Elemento | Tamanho | Peso | Exemplo |
|---------|--------|------|---------|
| `h1` | `2rem` (32px) | `700` | Título da página |
| `h2` | `1.75rem` (28px) | `600` | Seções principais |
| `h3` | `1.5rem` (24px) | `500` | Subseções |
| `body` | `1rem` (16px) | `400` | Texto regular |
| `caption` | `0.875rem` (14px) | `300` | Labels e notas |

### 2.3 Espaçamento
- **Line Height**: `1.5` para parágrafos, `1.2` para linhas de tabelas.
- **Margens**: Consistência com `tailwindcss` (ex: `mt-4`, `mb-6`).

---

## 3. Estrutura do Dashboard (Angular + Tailwind)
### 3.1 Layout Geral
- **Sidebar (Lateral)**:  
  - Navegação com links para:  
    - `Dívidas`  
    - `Pagamentos`  
    - `Relatórios`  
    - `Notificações`  
    - `Usuários`  
  - Usar `tailwindcss` `flex` para layout vertical com `w-64` e `h-screen`.

- **Header (Topo)**:  
  - Logo do sistema (`MeuGestor`) + avatar do usuário (dropdown com logout).  
  - Usar `flex` para alinhar elementos horizontalmente.

- **Main Content (Corpo)**:  
  - Dividido em seções com `grid` ou `flex` (ex: `grid-cols-1 md:grid-cols-3`).

### 3.2 Componentes Principais
- **Cards de Resumo**:  
  - Ex: "Total de Dívidas Pendentes: $1.500,00"  
  - Classes: `bg-white shadow-lg p-4 rounded-lg` (Tailwind).

- **Tabelas de Dados**:  
  - Para dívidas, pagamentos e usuários.  
  - Usar `tailwindcss` `table` com classes `divide-y divide-gray-200`.

- **Formulários**:  
  - Campos com validação em tempo real (ex: `required`, `pattern`).  
  - Estilo: `border border-gray-300 rounded` com feedback em `hover` e `focus`.

- **Gráficos (Relatórios)**:  
  - Usar bibliotecas como `Chart.js` integradas com Angular.  
  - Estilo: Fundo `bg-gray-100` com bordas `border-gray-300`.

### 3.3 Micro-Interações
- **Hover**:  
  - Botões: `bg-blue-600 hover:bg-blue-700`.  
  - Cards: `scale-105 transition-transform`.
- **Loading**:  
  - Spinner com `animate-spin` (Tailwind) e texto "Carregando...".

### 3.4 Responsividade
- **Mobile First**:  
  - Sidebar colapsada em `md:hidden` com ícone de menu.  
  - Tabelas convertidas para listas em `sm` e `md`.
- **Breakpoints**:  
  - `sm`: 640px  
  - `md`: 768px  
  - `lg`: 1024px  
  - `xl`: 1280px  

---

## 4. Considerações de Acessibilidade
- Todos os botões possuem `aria-label` e `role="button"`.
- Contraste de cores verificado com ferramentas de WCAG.
- Navegação com teclado suportada (focus visível).
- Textos alternativos para imagens e gráficos.