// My Books Data
const borrowedBooks = [
    {
        id: 1,

        title: 'Lạc Trong Rừng Sâu',
        author: 'Nguyễn Minh Tuấn',
        borrowDate: '2024-01-15',
        dueDate: '2024-02-15',
        daysLeft: 8,
        isOverdue: false,
        color: 'linear-gradient(135deg, #d946ef 0%, #ec4899 100%)'
    },
    {
        id: 2,
        title: 'Khoa Học Dữ Liệu Hiện Đại',
        author: 'Trần Văn Khoa',
        borrowDate: '2024-01-10',
        dueDate: '2024-02-10',
        daysLeft: 3,
        isOverdue: false,
        color: 'linear-gradient(135deg, #06b6d4 0%, #0ea5e9 100%)'
    },
    {
        id: 3,
        title: 'Đam Mê Lập Trình',
        author: 'Lý Thị Hoa',
        borrowDate: '2023-12-20',
        dueDate: '2024-01-20',
        daysLeft: -5,
        isOverdue: true,
        color: 'linear-gradient(135deg, #10b981 0%, #059669 100%)'
    }
];

const returnedBooks = [
    {
        id: 101,
        title: 'Quỷ Số 7',
        author: 'Phạm Quốc Anh',
        borrowDate: '2023-12-01',
        returnDate: '2024-01-05',
        color: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)'
    },
    {
        id: 102,
        title: 'Đặng Dậu: Cuộc Đời và Thừa Kế',
        author: 'Hồ Văn Minh',
        borrowDate: '2023-11-15',
        returnDate: '2023-12-20',
        color: 'linear-gradient(135deg, #eab308 0%, #ca8a04 100%)'
    }
];

// Format date
function formatDate(dateString) {
    const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
    return new Date(dateString).toLocaleDateString('vi-VN', options);
}

// Calculate progress percentage
function calculateProgress(daysLeft, totalDays = 31) {
    const daysUsed = totalDays - daysLeft;
    return Math.max(0, Math.min(100, (daysUsed / totalDays) * 100));
}

// Get days left color and text
function getDaysLeftInfo(daysLeft) {
    if (daysLeft < 0) {
        return {
            color: '#dc2626',
            text: `${Math.abs(daysLeft)} ngày quá hạn`,
            class: 'overdue'
        };
    }
    if (daysLeft <= 3) {
        return {
            color: '#ea580c',
            text: `${daysLeft} ngày`,
            class: 'warning'
        };
    }
    return {
        color: '#10b981',
        text: `${daysLeft} ngày`,
        class: 'safe'
    };
}

// Render borrowed books
function renderBorrowedBooks() {
    const list = document.getElementById('borrowedList');
    
    if (borrowedBooks.length === 0) {
        list.innerHTML = `
            <div class="empty-state">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                    <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                </svg>
                <h3>Bạn chưa mượn sách nào</h3>
                <p>Hãy đi tới thư viện để mượn sách yêu thích của bạn</p>
            </div>
        `;
        return;
    }

    list.innerHTML = borrowedBooks.map(book => {
        const daysInfo = getDaysLeftInfo(book.daysLeft);
        const progress = calculateProgress(book.daysLeft);

        return `
            <div class="book-item ${book.isOverdue ? 'overdue' : ''}">
                <div class="book-item-content">
                    <div class="book-item-cover" style="background: ${book.color};">
                        <svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="1">
                            <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                            <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                        </svg>
                    </div>
                    <div class="book-item-main">
                        <div class="book-item-header">
                            <div class="book-item-title-group">
                                <h3>${book.title}</h3>
                                <p>Tác giả: ${book.author}</p>
                            </div>
                            ${book.isOverdue ? `
                                <div class="book-item-badge">
                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                        <path d="M12 9v2m0 4v2m7.07-7.07a7 7 0 1 1-9.9 9.9M12 1a11 11 0 1 1 0 22 11 11 0 0 1 0-22z"></path>
                                    </svg>
                                    Quá Hạn
                                </div>
                            ` : ''}
                        </div>

                        <div class="book-dates">
                            <div class="date-item">
                                <span class="date-label">Ngày Mượn</span>
                                <div class="date-value">
                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                        <rect x="3" y="4" width="18" height="18" rx="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                    ${formatDate(book.borrowDate)}
                                </div>
                            </div>
                            <div class="date-item">
                                <span class="date-label">Hạn Trả</span>
                                <div class="date-value">
                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                        <rect x="3" y="4" width="18" height="18" rx="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                    ${formatDate(book.dueDate)}
                                </div>
                            </div>
                            <div class="date-item">
                                <span class="date-label">Còn Lại</span>
                                <div class="date-value ${daysInfo.class}">
                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                        <circle cx="12" cy="12" r="10"></circle>
                                        <polyline points="12 6 12 12 16 14"></polyline>
                                    </svg>
                                    ${daysInfo.text}
                                </div>
                            </div>
                        </div>

                        <div class="progress-bar">
                            <div class="progress-fill ${daysInfo.class}" style="width: ${progress}%"></div>
                        </div>

                        <div class="book-item-actions">
                            <button class="btn btn-primary" onclick="renewBook(${book.id})">
                                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                    <path d="M1 4v6h6"></path>
                                    <path d="M23 20v-6h-6"></path>
                                    <path d="M20.49 9A9 9 0 0 0 5.64 5.64M3.51 15A9 9 0 0 0 18.36 18.36"></path>
                                </svg>
                                Gia Hạn Sách
                            </button>
                            <button class="btn btn-outline" onclick="returnBook(${book.id})">
                                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                    <polyline points="3 6 5 4 7 6"></polyline>
                                    <line x1="5" y1="4" x2="5" y2="11"></line>
                                    <polyline points="21 18 19 20 17 18"></polyline>
                                    <line x1="19" y1="20" x2="19" y2="13"></line>
                                </svg>
                                Trả Sách
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

// Render returned books
function renderReturnedBooks() {
    const list = document.getElementById('returnedList');
    
    if (returnedBooks.length === 0) {
        list.innerHTML = `
            <div class="empty-state">
            
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                    <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                </svg>
                <h3>Chưa có sách được trả</h3>
                <p>Lịch sử trả sách sẽ xuất hiện ở đây</p>
            </div>
        `;
        return;
    }

    list.innerHTML = returnedBooks.map(book => `
        <div class="book-item">
            <div class="book-item-content">
                <div class="book-item-cover" style="background: ${book.color};">
                    <svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="1">
                        <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                        <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                    </svg>
                </div>
                <div class="book-item-main">
                    <h3>${book.title}</h3>
                    <p style="color: #6b7280; font-size: 0.875rem; margin-bottom: 1rem;">Tác giả: ${book.author}</p>

                    <div class="book-dates">
                        <div class="date-item">
                            <span class="date-label">Ngày Mượn</span>
                            <div class="date-value">
                                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                    <rect x="3" y="4" width="18" height="18" rx="2"></rect>
                                    <line x1="16" y1="2" x2="16" y2="6"></line>
                                    <line x1="8" y1="2" x2="8" y2="6"></line>
                                    <line x1="3" y1="10" x2="21" y2="10"></line>
                                </svg>
                                ${formatDate(book.borrowDate)}
                            </div>
                        </div>
                        <div class="date-item">
                            <span class="date-label">Ngày Trả</span>
                            <div class="date-value safe">
                                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                    <rect x="3" y="4" width="18" height="18" rx="2"></rect>
                                    <line x1="16" y1="2" x2="16" y2="6"></line>
                                    <line x1="8" y1="2" x2="8" y2="6"></line>
                                    <line x1="3" y1="10" x2="21" y2="10"></line>
                                </svg>
                                ${formatDate(book.returnDate)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `).join('');
}

// Tab switching
function switchTab(tabName) {
    const tabs = document.querySelectorAll('.tab-content');
    const buttons = document.querySelectorAll('.tab-btn');

    tabs.forEach(tab => tab.classList.remove('active'));
    buttons.forEach(btn => btn.classList.remove('active'));

    document.getElementById(tabName + 'Tab').classList.add('active');
    event.target.classList.add('active');
}

// Actions
function renewBook(bookId) {
    alert('Sách đã được gia hạn! (Demo)');
}

function returnBook(bookId) {
    alert('Sách đã được trả! (Demo)');
}

// Initialize
document.addEventListener('DOMContentLoaded', function() {
    // Render initial data
    renderBorrowedBooks();
    renderReturnedBooks();

    // Tab buttons
    const tabButtons = document.querySelectorAll('.tab-btn');
    tabButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            const tabName = this.getAttribute('data-tab');
            const tabs = document.querySelectorAll('.tab-content');
            
            tabs.forEach(tab => tab.classList.remove('active'));
            tabButtons.forEach(b => b.classList.remove('active'));

            document.getElementById(tabName + 'Tab').classList.add('active');
            this.classList.add('active');
        });
    });
});
