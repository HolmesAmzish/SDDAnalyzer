<center>
    <h1><img src="src/main/resources/static/img/sdd-logo.png" width="10%">  钢铁表面缺陷分析平台</h1>
    <h3>Steel Surface Defect Detection Analyzer</h3>
    <p style="white-space: nowrap;">
        <img src="https://img.shields.io/badge/Java-21-red?logo=java">
        <img src="https://img.shields.io/badge/Spring%20Boot-3.4.3-green?logo=spring">
        <img src="https://img.shields.io/badge/MySQL-8.0-teal?logo=mysql">
        <img src="https://img.shields.io/badge/Ollama-deepseekv3-white?logo=ollama">
    </p>
</center>

## 摘要

随着制造业对质量控制需求的提升，钢铁表面缺陷的智能化分析成为关键技术挑战。本研究开发了一款基于 Web 的钢铁表面缺陷分析平台（Steel Surface Defect Detection Analyzer, SDD Analyzer），采用 Spring Boot 3.4.3 框架构建，结合 MySQL 数据库和本地部署的 Ollama-DeepSeek v3 模型，实现缺陷数据的存储、统计分析与智能问答功能。平台通过用户验证确保访问安全，支持数据可视化图表绘制，并允许用户基于数据提出问题，由本地 AI 模型分析并回答。系统具有本地化部署、数据隐私保护、高扩展性和实时交互等特点，显著提升了工业质检效率与决策支持能力，为钢铁制造业提供了创新解决方案。 

## 1. 引言

钢铁表面的缺陷（如夹杂物、划痕等）直接影响产品质量和生产安全。传统检测依赖人工或独立设备，数据分散且分析效率低下，难以满足现代工业对实时性和智能化的需求。近年来，Web 技术和人工智能的结合为缺陷数据管理与分析提供了新路径。然而，现有系统多依赖云端 AI 服务，存在数据泄漏风险和高延迟问题。 本研究提出了一种本地化、智能化的钢铁表面缺陷分析平台，基于 Java 和 Spring Boot 框架开发，集成 MySQL 数据库和 Ollama 部署的 DeepSeek v3 模型。平台通过 Web 管理端提供用户验证、数据管理与可视化功能，支持用户基于缺陷数据提问并获取 AI 分析结果。其核心创新包括本地 AI 部署保障数据安全、高效的数据处理与实时交互能力。

## 2. 系统设计与实现 

### 2.1 系统架构
系统采用前后端分离架构，前端基于 Bootstrap 和 Chart.js 实现交互界面，后端通过 Spring Boot 提供 RESTful API 服务。整体流程如下： 

```mermaid
graph TD    
A[用户登录] --> B(Web 管理端)    
B --> C{功能模块}    
C -->|数据管理| D[MySQL 数据库]    
C -->|统计分析| E[Chart.js 图表]    
C -->|AI 问答| F[Ollama-DeepSeek v3]    
D --> G[数据服务]    
G --> E    
G --> F    
F --> H[分析结果]    
E --> I[可视化展示]    
H --> I
```

### 2.2 Web 管理端

**用户验证**：通过 Spring Security 实现基本的身份认证，用户需登录才能访问平台功能，确保数据访问安全。

**界面设计**：基于 Bootstrap 5.3.2，提供响应式布局和侧边导航栏，支持数据展示、统计分析和 AI 分析三大模块。

### 2.3 数据管理与存储

- **技术选型**：采用 MySQL 8.0 作为数据库，通过 MyBatis 3.0.4 实现数据访问。
- 实现逻辑：
  - ResultService 类提供数据查询服务（getAllResults 和 getResultById），从数据库提取缺陷记录（包括 ID、名称、日期、标签等）。
  - 数据通过 REST API（如 /api/data/getAll）传输至前端。
- **数据结构**：缺陷记录包括检测时间、标签（夹杂物、划痕等）、Dice 评分等字段。

### 2.4 数据可视化

- 实现方式

  ：前端使用 Chart.js 绘制三种图表：

  1. **饼图**：展示缺陷标签分布（如夹杂物、划痕占比）。
  2. **柱状图**：展示检测时间分布（分段为 0.05-0.15 秒）。
  3. **柱状图**：展示缺陷数量分布（0-4 个）。

- **动态更新**：通过 fetchData 函数从后端获取数据并实时刷新图表。

### 2.5 AI 分析与问答

- 本地部署：

  - 使用 Ollama 部署 DeepSeek v3 模型于本地服务器，避免数据传输至外部云端。
  - 优点：保障数据隐私，降低网络延迟。

- 实现逻辑：

  - DeepSeekController 通过 REST API（/api/ai/analyze）接收用户提问（prompt）。

  - 原代码调用外部 DeepSeek API，现改为本地 Ollama 接口（假设地址为 http://localhost:11434/api/chat）。

  - 示例请求：

    json