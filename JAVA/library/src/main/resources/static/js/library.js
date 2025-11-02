// Books Data
const books = [
    {
        id: 1,
        title: 'Lạc Trong Rừng Sâu',
        author: 'Nguyễn Minh Tuấn',
        category: 'fiction',
        rating: 4.8,
        reviews: 234,
        year: 2023,
        available: 5,
        color: 'linear-gradient(135deg, #d946ef 0%, #ec4899 100%)'
    },
    {
        id: 2,
        title: 'Khoa Học Dữ Liệu Hiện Đại',
        author: 'Trần Văn Khoa',
        category: 'science',
        rating: 4.6,
        reviews: 189,
        year: 2023,
        available: 3,
        color: 'linear-gradient(135deg, #06b6d4 0%, #0ea5e9 100%)'
    },
    {
        id: 3,
        title: 'Đam Mê Lập Trình',
        author: 'Lý Thị Hoa',
        category: 'non-fiction',
        rating: 4.7,
        reviews: 321,
        year: 2022,
        available: 7,
        color: 'linear-gradient(135deg, #10b981 0%, #059669 100%)'
    },
    {
        id: 4,
        title: 'Quỷ Số 7',
        author: 'Phạm Quốc Anh',
        category: 'fiction',
        rating: 4.9,
        reviews: 456,
        year: 2023,
        available: 2,
        color: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)'
    },
    {
        id: 5,
        title: 'Đặng Dậu: Cuộc Đời và Thừa Kế',
        author: 'Hồ Văn Minh',
        category: 'biography',
        rating: 4.5,
        reviews: 178,
        year: 2022,
        available: 4,
        color: 'linear-gradient(135deg, #eab308 0%, #ca8a04 100%)'
    },
    {
        id: 6,
        title: 'Vũ Trụ Vô Tận',
        author: 'Ngo Nhật Phương',
        category: 'science',
        rating: 4.4,
        reviews: 145,
        year: 2023,
        available: 6,
        color: 'linear-gradient(135deg, #8b5cf6 0%, #6d28d9 100%)'
    },
    {
        id: 7,
        title: 'Yêu Thương Vô Điều Kiện',
        author: 'Cao Thị Hương',
        category: 'fiction',
        rating: 4.7,
        reviews: 289,
        year: 2023,
        available: 8,
        color: 'linear-gradient(135deg, #f43f5e 0%, #e11d48 100%)'
    },
    {
        id: 8,
        title: 'Kinh Tế Hành Vi',
        author: 'Đỗ Xuân Sơn',
        category: 'non-fiction',
        rating: 4.6,
        reviews: 267,
        year: 2022,
        available: 3,
        color: 'linear-gradient(135deg, #06b6d4 0%, #0891b2 100%)'
    }
];

// Render Books
function renderBooks(booksToRender) {
    const grid = document.getElementById('booksGrid');
    
    if (booksToRender.length === 0) {
        grid.innerHTML = `
            <div style="grid-column: 1 / -1;">
                <div class="empty-state">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                        <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                    </svg>
                    <h3>Không tìm thấy sách</h3>
                    <p>Hãy thử tìm kiếm với từ khóa khác</p>
                </div>
            </div>
        `;
        return;
    }

    grid.innerHTML = booksToRender.map(book => `
        <div class="book-card">
            <div class="book-cover" style="background: ${book.color};">
                <svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="1">
                    <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                    <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                </svg>
                <div class="book-badge ${book.available === 0 ? 'unavailable' : ''}">
                    ${book.available > 0 ? `${book.available} Sẵn` : 'Hết'}
                </div>
            </div>
            <div class="book-info">
                <h3 class="book-title">${book.title}</h3>
                <p class="book-author">Tác giả: ${book.author}</p>
                <div class="book-rating">
                    <div class="book-stars">
                        ${[...Array(5)].map((_, i) => `
                            <span class="book-star">${i < Math.floor(book.rating) ? '★' : '☆'}</span>
                        `).join('')}
                    </div>
                    <span class="book-rating-text">${book.rating} (${book.reviews})</span>
                </div>
                <div class="book-date">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <rect x="3" y="4" width="18" height="18" rx="2"></rect>
                        <line x1="16" y1="2" x2="16" y2="6"></line>
                        <line x1="8" y1="2" x2="8" y2="6"></line>
                        <line x1="3" y1="10" x2="21" y2="10"></line>
                    </svg>
                    <span>${book.year}</span>
                </div>
                <button class="book-borrow-btn" ${book.available === 0 ? 'disabled' : ''}>
                    ${book.available > 0 ? 'Mượn Sách' : 'Hết Hàng'}
                </button>
            </div>
        </div>
    `).join('');
}

// Filter Books
function filterBooks() {
    const searchQuery = document.getElementById('searchInput').value.toLowerCase();
    const activeCategory = document.querySelector('.filter-btn.active')?.getAttribute('data-category') || 'all';

    const filtered = books.filter(book => {
        const matchesSearch = book.title.toLowerCase().includes(searchQuery) || 
                            book.author.toLowerCase().includes(searchQuery);
        const matchesCategory = activeCategory === 'all' || book.category === activeCategory;
        return matchesSearch && matchesCategory;
    });

    renderBooks(filtered);
}

// Initialize
document.addEventListener('DOMContentLoaded', function() {
    // Render initial books
    renderBooks(books);

    // Search functionality
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', filterBooks);
    }

    // Category filter functionality
    const filterButtons = document.querySelectorAll('.filter-btn');
    filterButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            filterButtons.forEach(b => b.classList.remove('active'));
            this.classList.add('active');
            filterBooks();
        });
    });

    // Borrow book functionality
    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('book-borrow-btn') && !e.target.disabled) {
            alert('Sách đã được mượn! (Demo)');
        }
    });
});
