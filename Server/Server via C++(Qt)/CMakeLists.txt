cmake_minimum_required(VERSION 2.8.12)

project(Server_app)

set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(CMAKE_AUTOMOC ON)
set( QT_VERSION_REQ "5.11.1")
find_package(Qt5Core ${QT_VERSION_REQ} REQUIRED)
find_package(Qt5Sql ${QT_VERSION_REQ} REQUIRED)
find_package(Qt5Network ${QT_VERSION_REQ} REQUIRED)

add_executable(${PROJECT_NAME} "main.cpp")

target_link_libraries(${PROJECT_NAME}
    Qt5::Core
    Qt5::Network
    Qt5::Sql
    )
