document.addEventListener('DOMContentLoaded', function() {
    // 1. Lấy đường dẫn hiện tại trên thanh địa chỉ (ví dụ: /shop/theloai)
    const currentPath = window.location.pathname;

    // 2. Lấy tất cả các link trong menu
    const navLinks = document.querySelectorAll('.nav-link, .mobile-nav-link');

    navLinks.forEach(link => {
        // Xóa sạch class active cũ
        link.classList.remove('active');

        // Lấy đường dẫn từ thuộc tính href của thẻ <a>
        // link.pathname sẽ trả về đường dẫn chuẩn (ví dụ: /shop/theloai)
        const linkPath = link.pathname;

        // KIỂM TRA LOGIC
        if (linkPath === '/' || linkPath.endsWith('index.html')) {
            // Trang chủ: chỉ xanh khi đường dẫn thực sự là / hoặc index.html
            if (currentPath === '/' || currentPath.endsWith('index.html')) {
                link.classList.add('active');
            }
        } else {
            // Các trang khác (Shop, Thể loại...):
            // Nếu URL hiện tại bắt đầu bằng linkPath thì mới thêm active
            // Ví dụ: currentPath là /shop/theloai chứa linkPath là /shop/theloai
            if (currentPath === linkPath || (linkPath !== '/' && currentPath.startsWith(linkPath))) {
                link.classList.add('active');
            }
        }
    });

    // --- 2. MOBILE MENU TOGGLE ---
    const mobileMenuBtn = document.getElementById('mobileMenuBtn');
    const mobileNav = document.getElementById('mobileNav');
    if (mobileMenuBtn && mobileNav) {
        mobileMenuBtn.addEventListener('click', function() {
            mobileNav.style.display = mobileNav.style.display === 'none' ? 'block' : 'none';
        });
    }

    // --- 3. LOGIN & REGISTER HANDLER (Giữ nguyên logic của bạn) ---
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Đăng nhập thành công! (Demo)');
        });
    }
});

// --- 4. TOGGLE PASSWORD ---
function togglePassword(fieldId) {
    const field = document.getElementById(fieldId);
    if (field) field.type = (field.type === 'password') ? 'text' : 'password';
}