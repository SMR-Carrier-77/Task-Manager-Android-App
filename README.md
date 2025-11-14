# 🗂️ Task Manager App

A simple and easy Task Manager built with **Kotlin** and **Room Database**.  
Users can create, edit, delete, and manage tasks with due dates.  
The app uses MVVM basics, RecyclerView UI, and local storage with Room.

---

## 🚀 Features

- **Add Tasks** – Create tasks with title, description, due date, and status  
- **Edit Tasks** – Update existing tasks with pre-filled data  
- **Delete Tasks** – Remove tasks with a confirmation dialog  
- **View All Tasks** – Tasks appear in a RecyclerView list  
- **Room Database** – Local persistent storage  
- **Status Support** – Mark tasks as completed or pending  
- **Auto Sort by Due Date** – Shows nearest dates first  
- **Parcelable Data Passing** – Smooth edit/update operations  
- **Clean UI** – Simple and lightweight design

---

## 🧱 Tech Stack

| Component | Technology |
|----------|------------|
| Language | Kotlin |
| Database | Room (Local Storage) |
| UI | RecyclerView, Material Design |
| Architecture | MVVM (Basic Structure) |
| Async Operations | Coroutines + LiveData *(optional for future)* |
| Adapter/Binding | RecyclerView Adapter, ViewBinding |
| Android Version | API 24+ (Android 7.0 and above) |

---

## 📦 Core Files

### **1. Task.kt (Entity + Parcelable)**
- Stores task data  
- Fields: id, title, description, due date, completion status  
- Uses `@Entity` and `@Parcelize`

### **2. TaskAdapter.kt**
Handles:
- Binding task information to UI  
- Edit button listener  
- Delete confirmation dialog  
- Converts timestamp to readable date  

### **3. AddTaskActivity**
- Add new tasks  
- Edit existing tasks  
- DatePicker for due date  
- Input validation  
- Insert / update tasks in Room database  

### **4. MainActivity**
- Loads all tasks  
- Shows them in a RecyclerView  
- Edit and delete callbacks  
- Auto refresh on resume  

---

## 🗃️ Database Structure

**Entity: Task**

| Field | Type | Description |
|-------|------|-------------|
| id | Int | Auto-generated primary key |
| title | String | Task title |
| description | String | Task description |
| dueDate | String | Timestamp in milliseconds |
| isCompleted | Boolean | Completion status |

---

## 📲 How to Run

```bash
git clone https://github.com/SMR-Carrier-77/Task-Manager-Android-App.git
```

Open the project in Android Studio, sync Gradle and run the app.

---

---

## 👨‍💻 Author

### **Most Sidratun Muntahar**
Android App Developer | Computer Science Student  
📧 Email:sidratunmuntaharsmr77@gmail.com  
🌍 Rangpur, Bangladesh  
🌐 Website: https://smrcarrier77.blogspot.com/  

Built with Kotlin and a lot of patience.

